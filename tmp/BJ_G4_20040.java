/**

 @author 한규준
 @since 2023-10-07
 @see https://www.acmicpc.net/problem/20040
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_20040.java
 @youtube
 @performance 251688KB, 1056ms
 @category 부분집합, union find
 @note

 점 부분집합 내에서 사이클이 이어지려면
 시작점, 출발점이 새로운 선분이자 한 집합 내의 다른 두 점이어야 한다.
 문제의 조건에서 선분은 항상 새로운 선분임이 나타나 있으므로
 union-find로 처리

 */

package algo.src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_20040 {
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = i;
        }

        int result = 0;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(union(a, b)){
                if(result == 0) result = i + 1;
            }
        }

        System.out.println(result);
    }

    static private int find(int a){
        if(arr[a] == a) return a;
        return arr[a] = find(arr[a]);
    }

    static private boolean union(int a, int b){
        int pA = find(a);
        int pB = find(b);
        if(pA == pB) return true;
        arr[pB] = pA;
        return false;
    }
}
