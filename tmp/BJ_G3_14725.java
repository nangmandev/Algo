package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BJ_G3_14725 {
    public static Node rootNode;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 루트노드를 링크드리스트로 표현하고
        // 각 루트노드를 트리로 만든다

        int N = Integer.parseInt(br.readLine());

        // 가상의 루트노드를 만들어서 관리한다.
        rootNode = new Node("root", 0, null);

        String[] tmp;
        Node now;

        for(int i = 0; i < N; i++){
            tmp = br.readLine().split(" ");
            // depth = level = 배열의 주소와 같다.
            now = rootNode;
            for(int j = 1; j < tmp.length; j++){
                // 해당 노드의 자식을 쭉 돌면서 있는지 확인한다.
                boolean isExists = false;
                for(int k = 0; k < now.childs.size(); k++){
                    if(now.childs.get(k).name.equals(tmp[j])){
                        isExists = true;
                        now = now.childs.get(k);
                        break;
                    }
                }
                // 해당 노드가 없으면 추가
                if(!isExists){
                    Node newChild = new Node(tmp[j], j, now);
                    now.childs.add(newChild);
                    now = newChild;
                }
            }
        }

        // 전위탐색
        pre(rootNode, sb);

        System.out.print(sb);

    }

    public static void pre(Node node, StringBuilder sb){
        if(!node.name.equals("root")) {
            for(int i = 0; i < node.level - 1; i++){
                sb.append("--");
            }
            sb.append(node.name + "\n");
        }
        Collections.sort(node.childs);
        for(int i = 0; i < node.childs.size(); i++){
            pre(node.childs.get(i), sb);
        }
    }

    public static class Node implements Comparable<Node> {
        String name;
        int level;
        Node parent;
        List<Node> childs;

        Node(String name, int level, Node parent){
            this.name = name;
            this.level = level;
            this.parent = parent;
            childs = new ArrayList<>();
        }

        @Override
        public int compareTo(Node o) {
            return this.name.compareTo(o.name);
        }
    }
}
