/**

 @author 한규준
 @since 2023-10-10
 @see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWngfZVa9XwDFAQU&probBoxId=AYsWdiua8IADFAV6&type=PROBLEM&problemBoxTitle=1010%EC%A3%BC&problemBoxCnt=2
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D4_7465.java
 @youtube
 @performance 24888KB, 134ms
 @category 집합, union-find
 @note

 1. 서로 알거나 어떠한 사람을 통해 알 수 있다면 하나의 무리입니다.
 2. 그러므로, 해당하는 사람이 같은 무리임을 판단하기 위해서 그래프탐색 혹은 집합이론을 사용해야 합니다.
 3. 그래프 탐색을 이용하는 경우 N회 순회하며 BFS/DFS로 무리 사람들을 visited표시를 한 후, BFS/DFS가 돌 수 있는 횟수를 셉니다.
 4. 집합을 이용하는 경우 union-find로 루트노드가 자기 자신인 경우의 갯수를 세면 됩니다.
 5. union-find를 이용하는 코드가 더 간결할 것으로 예상해 union-find방식으로 풀이를 진행하였습니다.

 */

package algo.src.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_D4_7465 {
    static int[] parents;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            parents = new int[N + 1];

            for (int i = 0; i <= N; i++) {
                parents[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            int result = 0;
            for (int i = 1; i <= N; i++) {
                if (parents[i] == i) result++;
            }

            bw.write("#" + t + " " + result + "\n");
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
