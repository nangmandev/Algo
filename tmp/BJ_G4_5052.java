package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ_G4_5052 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            Node root = new Node('r');

            boolean existPre = false;

            for (int i = 0; i < N; i++) {
                Node now = root;
                if (existPre) {
                    br.readLine();
                    continue;
                }
                String number = br.readLine();
                boolean created = false;
                for (int j = 0; j < number.length(); j++) {
                    // 하위 노드를 확인
                    Node next = now;
                    // 하위 노드 중에 있으면 따라간다.
                    for (int k = 0; k < now.childs.size(); k++) {
                        if (now.childs.get(k).num == number.charAt(j)) {
                            next = now.childs.get(k);
                        }
                    }

                    // 자식노드가 있는데 따라가지 않은경우
                    // 루트노드인경우
                    // 새로 만들어지는 중인 경우
                    if ((!now.childs.isEmpty() && now == next)
                            || (now.num == 'r' && now == next)
                        || created) {
                        Node newNode = new Node(number.charAt(j));
                        now.childs.add(newNode);
                        next = newNode;
                        created = true;
                    }

                    // 자식노드가 없고, 분기가 갈라지는 경우
                    else if (now.childs.isEmpty() && now == next) {
                        existPre = true;
                        break;
                    }

                    // 자신은 끝인데 새로만든 노드가 없는경우
                    else if (j == number.length() - 1 && !created) {
                        existPre = true;
                        break;
                    }

                    // 이동
                    now = next;
                }
            }

            if (existPre) {
                sb.append("NO\n");
            } else {
                sb.append("YES\n");
            }
        }
        System.out.print(sb);
    }

    public static class Node{
        char num;
        List<Node> childs;
        Node(char num){
            this.num = num;
            this.childs = new ArrayList<>();
        }
    }
}
