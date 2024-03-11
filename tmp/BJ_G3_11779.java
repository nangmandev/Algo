/**

 @author 한규준
 @since 2023-08-09
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_11779.java
 @youtube
 @performance 49832KB, 388ms
 @category 다익스트라, 거꾸로 생각하기
 @note

 1. 다익스트라로 모든 최단경로 구하면서
 2. 방문한 모든 노드를 기록하는데
 3. 이때, 노드만큼의 배열에 각 노드 인덱스에 이전 방문 노드를 기록하면 됨(갱신걱정 X)
 4. 갱신이 왜 안되냐면, 같은곳에 중복접근하는데 최단경로가 아닌경우 컷됨
 5. 이후 도착지점의 인덱스에 접근, 도착지점의 내용을 다음 인덱스로 ... 반복
 6. 0에 도착하면 반복문에서 빠져나오면 됩니다.(시작노드는 이전노드가 0이기 때문)
 7. 해당 리스트를 거꾸로 출력하면 경로 출력됩니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BJ_G3_11779 {
    static final int INF = 987654321;

    public static class Node{
        int next;
        int cost;

        public Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Node>> cities = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        int[] visited = new int[N + 1];
        int[] distance = new int[N + 1];
        for(int i = 0; i <= N; i++){
            distance[i] = INF;
        }

        for(int i = 0; i <= N; i++){
            cities.add(new ArrayList<Node>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            cities.get(start).add(new Node(end, cost));
        }

        // 세팅끝

        st = new StringTokenizer(br.readLine());
        int startA = Integer.parseInt(st.nextToken());
        int endB = Integer.parseInt(st.nextToken());

        Deque<Node> nodes = new ArrayDeque<>();
        Node FirstNode = new Node(startA, 0);
        nodes.add(FirstNode);
        distance[startA] = 0;

        while(!nodes.isEmpty()){
            Node nowNode = nodes.poll();
            if(distance[nowNode.next] < nowNode.cost) {
                continue;
            }

            for(int i = 0; i < cities.get(nowNode.next).size(); i++){
                Node nextNode = cities.get(nowNode.next).get(i);
                if(distance[nextNode.next] > nowNode.cost + nextNode.cost){
                    distance[nextNode.next] = nowNode.cost + nextNode.cost;
                    visited[nextNode.next] = nowNode.next;
                    nodes.add(new Node(nextNode.next, distance[nextNode.next]));
                }
            }
        }

        int temp = visited[endB];
        result.add(endB);
        while(temp != 0){
            result.add(temp);
            temp = visited[temp];
        }

        bw.write(distance[endB] + "\n");
        bw.write( result.size() + "\n");
        for(int i = result.size() - 1; i >= 0; i--){
            bw.write(result.get(i) + " ");
        }

        bw.flush();
        bw.close();
    }
}