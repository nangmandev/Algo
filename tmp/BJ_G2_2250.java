package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ_G2_2250 {
    static List<Node> nodes;
    static List<Side> sides;
    static int x;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nodes = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i <= N; i++) {
            nodes.add(new Node());
            nodes.get(i).parent = -1;
        }

        // 노드번호는 i - 1
        String[] tmp;
        int parent, left, right;
        for(int i = 1; i <= N; i++){
            tmp = br.readLine().split(" ");
            parent = Integer.parseInt(tmp[0]);
            left = Integer.parseInt(tmp[1]);
            right = Integer.parseInt(tmp[2]);

            nodes.get(parent).left = left;
            nodes.get(parent).right = right;
            if(left != -1) nodes.get(left).parent = parent;
            if(right != -1) nodes.get(right).parent = parent;
        }

        // start를 찾아야 한다. rootnode
        // 적당히 하나 골라서 parent == -1될때까지 이동
        int start = 1;
        while(nodes.get(start).parent != -1){
            start = nodes.get(start).parent;
        }

        // 이제 start는 root를 가리킨다.
        // 각 레벨별 가장 좌측, 우측을 가리킬 배열
        sides = new ArrayList<>();
        for(int i = 0; i < 10001; i++) sides.add(new Side());

        x = 1;
        mid(start, 1);

        int maxLength = 1;
        int level = 1;

        for(int i = 1; i < sides.size(); i++){
            if(sides.get(i).right == Integer.MIN_VALUE
            || sides.get(i).left == Integer.MAX_VALUE) {
                continue;
            }

            if(maxLength < sides.get(i).right - sides.get(i).left + 1){
                maxLength = sides.get(i).right - sides.get(i).left + 1;
                level = i;
            }
        }

        System.out.println(level + " " + maxLength);
    }

    public static void mid(int node, int level){
        // 현재 레벨의 좌/우측 확인
        if(nodes.get(node).left != -1){
            mid(nodes.get(node).left, level + 1);
        }

        nodes.get(node).x = x++;

        if(sides.get(level).left > nodes.get(node).x){
            sides.get(level).left = nodes.get(node).x;
        }

        if(sides.get(level).right < nodes.get(node).x){
            sides.get(level).right = nodes.get(node).x;
        }

        if(nodes.get(node).right != -1){
            mid(nodes.get(node).right, level + 1);
        }
    }

    public static class Node{
        int parent;
        int left;
        int right;
        int x;
    }

    public static class Side{
        int left;
        int right;
        Side(){
            left = Integer.MAX_VALUE;
            right = Integer.MIN_VALUE;
        }
    }
}
