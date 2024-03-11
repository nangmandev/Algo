/**

 @author 한규준
 @since 2023-08-02
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D4_8275.java
 @youtube
 @performance 20980KB, 125ms
 @category BruteForce, 중복순열
 @note

 케이스를 따져보았을 때, 한 자리에 숫자가 하나씩 들어가고
 중복인 경우가 있으므로 중복순열 문제입니다.
 가장 합이 큰 경우가 우선시되므로 햄스터를 큰 수부터 넣어보면 되고
 사전 순서대로 우선시되므로 거꾸로 찾아가면서 가장 먼저 발견하는 수열을 찾으면 됩니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_D4_8275 {
    // 조건에 부합하는 순열 발견을 표시하는 플래그
    private static int flag;
    // 순열 결과
    private static int[] result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer temp;

        for(int t = 1; t <= T; t++){
            // 플래그 초기화 및 변수들 입력받기
            flag = 0;
            temp = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(temp.nextToken()), X = Integer.parseInt(temp.nextToken()), M = Integer.parseInt(temp.nextToken());
            int[][] hap = new int[M][3];
            for(int caseM = 0; caseM < M; caseM++){
                temp = new StringTokenizer(br.readLine());
                hap[caseM][0] = Integer.parseInt(temp.nextToken());
                hap[caseM][1] = Integer.parseInt(temp.nextToken());
                hap[caseM][2] = Integer.parseInt(temp.nextToken());
            }

            // 중복순열 만들기 시작
            makePermutationDup(N, new int[N + 1], X, hap);

            // 출력
            if(flag == 0) bw.write("#" + t + " " + -1 + "\n");
            else {
                bw.write("#" + t + " ");
                for(int i = 1; i <= N; i++){
                    bw.write(result[i] + " ");
                }
                bw.write("\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static void makePermutationDup(final int nth, int[] cage, int maxMari, int[][] hap){
        // 만약 이미 조건을 만족하는 수열이 나온 경우 더 이상 수행 X
        // 거꾸로 진행하므로, 처음 찾는 수열이 최대 크기입니다.
        // X마리 -> 0마리로, 수열의 마지막 자리에서 처음 자리로.
        if(flag == 1) return;
        if(nth == 0){
            // 찾은 경우 복사
            // 그냥 넘기면 call by value가 발생합니다.
            flag = 1;
            result = cage.clone();
            return;
        }
        for(int i = maxMari; i >= 0; i--){
            // X -> 0으로 거꾸로 대입하면서 중복수열을 찾습니다.
            cage[nth] = i;
            // 일단 대입해보고
            // 조건이 맞는지 체크한 뒤
            if(hapCheck(nth, cage, hap)){
                // 맞으면 다음자리 찾으러 가기.
                makePermutationDup(nth - 1, cage, maxMari, hap);
            }
        }
    }

    private static boolean hapCheck(int nth, int[] cage, int[][] hap){
        for(int i = 0; i < hap.length; i++){
            // 뒤에서부터 찾는 것이므로, 앞자리까지 오면 확인합니다.(앞자리까지 와야 전체합 확인가능)
            if(hap[i][0] == nth){
                int sum = 0;
                // 앞에서부터 끝까지 합을 구한 뒤 확인
                for(int j = hap[i][0]; j <= hap[i][1]; j++){
                    sum += cage[j];
                }
                // 조건이 하나라도 맞지 않으면 false 반환
                if(sum != hap[i][2]) return false;
            }
        }
        // 모든 조건을 통과하면 true 반환
        return true;
    }
}
