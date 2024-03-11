/**

 @author 한규준
 @since 2023-10-08
 @see https://www.acmicpc.net/problem/1976
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_1976.java
 @youtube
 @performance 16076KB, 128ms
 @category 집합
 @note

그래프로 가는 길이 있으면 같은 집합에 추가한다
 중복해서 방문할 수 있으므로, 여행지들이 같은 집합에 속하기만 하면 된다.

 */

package algo.src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_1976 {
    static int[] parent;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];

        for(int i = 0; i <= N; i++){
            parent[i] = i;
        }

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                int to = Integer.parseInt(st.nextToken());
                if(to == 1){
                    union(i, j);
                }
            }
        }

        String result = "YES";
        st = new StringTokenizer(br.readLine());
        int count = find(Integer.parseInt(st.nextToken()));
        for(int i = 1; i < M; i++){
            int tmp = find(Integer.parseInt(st.nextToken()));
            if(tmp != count) {
                result = "NO";
            }
        }

        System.out.println(result);
    }

    private static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b){
        int pA = find(a);
        int pB = find(b);
        if(pA == pB) return;
        parent[pB] = pA;
    }
}
