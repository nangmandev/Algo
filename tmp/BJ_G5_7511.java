/**

 @author 한규준
 @since 2023-10-10
 @see https://www.acmicpc.net/problem/7511
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_7511.java
 @youtube
 @performance 164060KB, 1136ms
 @category 집합, union-find
 @note

주어진 두 사람이 같은 집합인지 판별

 */

package algo.src.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G5_7511 {
    static int[] parents;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int t = 1; t <= tc; t++) {
            bw.write("Scenario " + t + ":\n");
            int N = Integer.parseInt(br.readLine());
            int K = Integer.parseInt(br.readLine());
            parents = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            int M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                if (find(u) == find(v)) {
                    bw.write("1\n");
                } else {
                    bw.write("0\n");
                }
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    private static int find(int a){
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    private static void union(int a, int b){
        int pA = find(a);
        int pB = find(b);
        if(pA != pB){
            parents[pB] = pA;
        }
    }
}
