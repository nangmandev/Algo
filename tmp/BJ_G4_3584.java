/**

 @author 한규준
 @since 2023-09-16
 @see https://www.acmicpc.net/problem/3584
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_3584.java
 @youtube
 @performance 27912KB, 300ms
 @category LCA
 @note

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G4_3584 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int i = 0; i < tc; i++){
            int N = Integer.parseInt(br.readLine());

            ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
            for(int j = 0; j <= N; j++){
                edges.add(new ArrayList<>());
            }
            int[][] tree = new int[N + 1][2];
            for(int j = 1; j < N; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                tree[b][0] = a;
                edges.get(a).add(b);
            }

            int start = 1;
            while(true){
                if(tree[start][0] == 0) break;
                else {
                    start = tree[start][0];
                }
            }

            // start는 루트노드에 위치
            tree[start][1] = 1;
            Deque<Integer> deq = new ArrayDeque<>();
            deq.offer(start);

            while(!deq.isEmpty()){
                int now = deq.poll();

                for(int j = 0; j < edges.get(now).size(); j++){
                    int next = edges.get(now).get(j);
                    tree[next][1] = tree[now][1] + 1;
                    deq.offer(next);
                }
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(tree[b][1] > tree[a][1]){
                int temp = a;
                a = b;
                b = temp;
            }

            while(true){
                if(tree[a][1] == tree[b][1]){
                    break;
                }
                a = tree[a][0];
            }

            while(true){
                if(tree[a][0] == tree[b][0]){
                    break;
                }
                a = tree[a][0];
                b = tree[b][0];
            }

            if(a == b) bw.write(a + "\n");
            else bw.write(tree[a][0] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
