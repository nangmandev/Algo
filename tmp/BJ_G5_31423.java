package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_G5_31423 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 신촌 지역 N개 대학교가 하나로 통합
        // N개 대학교의 이름을 붙여 학교 이름이 정해졌다.

        // N개 대학교의 이름 s1~sn을 일렬로 나눈 다음 아래의 과정을 N-1번 반복
        // si, sj가 빈 문자열이 아닌 서로 다른 두 정수 i, j를 고른다.
        // si의 뒤쪽에 sj를 붙인다.
        // sj를 빈 문자열로 바꾼다.
        // 모든 과정이 끝나면 빈 문자열이 아닌 sk가 하나 남는데, 이것이 통합된 학교의 이름이 된다.
        // N개 대학교의 이름과 i, j가 순서대로 주어질 때, 통합된 학교의 이름을 구하는 프로그램

        // 노드는 연결리스트로 되어있고
        // 각 노드는 head, tail정보를 가진다.
        // 본인이 head면 tail로 꼬리를 물고
        // 본인이 head가 아니면 head로 이동한다.
        // -> 붙이기
        // 마지막 반복때 본체를 찍는다.

        int N = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[N + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(br.readLine());
        }

        // 초기화 완료

        int a, b, last = 0;
        String[] str;
        for (int i = 1; i < N; i++) {
            str = br.readLine().split(" ");
            a = Integer.parseInt(str[0]);
            b = Integer.parseInt(str[1]);

            Node head = nodes[a];
            Node tail = nodes[b];

            // head
            head.tail.rear = tail;
            tail.head = head.head;
            head.tail = tail.tail;

            if(i == N - 1){
                last = a;
            }
        }

        Node now = nodes[last];
        while(true){
            sb.append(now.name);
            if(now.rear == null) break;
            now = now.rear;
        }

        System.out.print(sb);

    }

    public static class Node {
        String name;
        Node head;
        Node tail;
        Node rear;

        Node(String name) {
            this.name = name;
            head = this;
            tail = this;
            rear = null;
        }
    }
}
