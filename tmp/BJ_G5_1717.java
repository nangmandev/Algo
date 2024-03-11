/**

 @author 한규준
 @since 2023-10-09
 @see https://www.acmicpc.net/problem/1717
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_1717.java
 @youtube
 @performance 49140KB, 540ms
 @category union find, 집합
 @note



 */

package algo.src.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G5_1717 {
    static int[] parent;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];

        for(int i = 0; i <= N; i++){
            parent[i] = i;
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(n == 0){
                union(a, b);
            } else {
                if(find(a) == find(b)) bw.write("YES\n");
                else bw.write("NO\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b){
        int pA = find(a);
        int pB = find(b);
        if(pA == pB) return false;
        parent[pB] = pA;
        return true;
    }
}
