/**

 @author 한규준
 @since 2023-08-08
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_14938.java
 @youtube
 @performance 12176KB, 108ms
 @category 플로이드 워셜
 @note

 플로이드 워셜로 거리 전부 구하고
 행 합 중 최대값 구하기
 0으로 초기화하지 말기
 INF쓰기


 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_14938 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[] itemAtNode = new int[N];
        int[][] roadToNode = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            itemAtNode[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(i == j) continue;
                roadToNode[i][j] = 1000;
            }
        }

        int node1, node2, weight;
        for(int i = 0; i < R; i++){
            st =  new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken()) - 1;
            node2 = Integer.parseInt(st.nextToken()) - 1;
            weight = Integer.parseInt(st.nextToken());
            roadToNode[node1][node2] = weight;
            roadToNode[node2][node1] = weight;
        }
        // 초기화 완료

        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(i == j) continue;
                    roadToNode[i][j] = Math.min(roadToNode[i][j], roadToNode[i][k] + roadToNode[k][j]);
                }
            }
        }

        int maxValue = 0;
        for(int i = 0; i < N; i++){
            int tempSum = 0;
            for(int j = 0; j < N; j++){
                if(roadToNode[i][j] <= M) tempSum += itemAtNode[j];
            }
            maxValue = Math.max(tempSum, maxValue);
        }

        System.out.println(maxValue);

    }
}
