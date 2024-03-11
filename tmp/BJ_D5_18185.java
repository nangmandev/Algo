/**

 @author 한규준
 @since 2023-08-09
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_D5_18185.java
 @youtube
 @performance 13012KB, 112ms
 @category 구현, 예외처리
 @note

1. 그냥 써서 구현하면 무조건 틀림
 2. 무조건 3번이나 2번을 먼저쓰면 안되는 케이스 고려


 */
package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_D5_18185 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int maxValue = 0;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 초기화 끝

        int min;
        for(int i = 0; i < N; i++){
            while(true) {
                if(i < N - 2 && arr[i] > 0 && arr[i + 1] > 0 && arr[i + 2] > 0 && arr[i + 1] > arr[i + 2]){
                    min = Math.min(arr[i], arr[i + 1] - arr[i + 2]);
                    arr[i] -= min;
                    arr[i + 1] -= min;
                    maxValue += min * 5;
                    if(arr[i] == 0 && arr[i + 1] == 0) {
                        i += 1;
                        break;
                    }
                } else if(i < N - 2 && arr[i] > 0 && arr[i + 1] > 0 && arr[i + 2] > 0){
                    min = Math.min(arr[i], arr[i + 1]);
                    min = Math.min(arr[i + 2], min);
                    arr[i] -= min;
                    arr[i + 1] -= min;
                    arr[i + 2] -= min;
                    maxValue += min * 7;
                    if(arr[i] == 0 && arr[i + 1] == 0){
                        i += 1;
                        break;
                    } else if(arr[i] == 0 && arr[i + 1] == 0 && arr[i + 2] == 0){
                        i += 2;
                        break;
                    }
                } else if(i < N - 1 && arr[i] > 0 && arr[i + 1] > 0){
                    min = Math.min(arr[i], arr[i + 1]);
                    arr[i] -= min;
                    arr[i + 1] -= min;
                    maxValue += min * 5;
                    if(arr[i] == 0 && arr[i + 1] == 0){
                        i += 1;
                        break;
                    }
                } else if(arr[i] > 0){
                    maxValue += arr[i] * 3;
                    arr[i] = 0;
                    break;
                }
                if(arr[i] == 0) break;
            }
        }

        bw.write(maxValue + "\n");
        bw.flush();
        bw.close();
    }
}
