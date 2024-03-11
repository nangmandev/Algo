/**

 @author 한규준
 @since 2023-09-15
 @see https://www.acmicpc.net/problem/1005
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_1005_2.java
 @youtube
 @performance 250544KB, 816ms
 @category 위상정렬, DP
 @note

 애매한 DP
 ArrayList사용시 시간을 줄일 수 있다.
 */
package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BJ_G3_1005_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] D = new int[N + 1];
            int[] sum = new int[N + 1];
            ArrayList<ArrayList<Integer>> depends = new ArrayList<>();
            depends.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                D[i] = Integer.parseInt(st.nextToken());
                sum[i] = D[i];
                depends.add(new ArrayList<>());
            }

            int[] level = new int[N + 1];
            for (int i = 1; i <= K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                depends.get(a).add(b);
                level[b]++;
            }

            Deque<Integer> deq = new ArrayDeque<>();
            for(int i = 1; i <= N; i++) {
                if(level[i] == 0) {
                    deq.offer(i);
                }
            }

            int dest = Integer.parseInt(br.readLine());

            while(!deq.isEmpty()) {
                int temp = deq.poll();
                for(int i = 0; i < depends.get(temp).size(); i++){
                    int to = depends.get(temp).get(i);
                    if(level[to] != 0){
                        sum[to] = Math.max(sum[to], sum[temp] + D[to]);
                        if(--level[to] == 0){
                            deq.offer(to);
                        }
                    }
                }
                if(temp == dest) break;
            }

            bw.write(sum[dest] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
