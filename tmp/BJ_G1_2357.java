/**

 @author 한규준
 @since 2023-08-12
 @see https://www.acmicpc.net/problem/2357
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_2357.java
 @youtube
 @performance 77540KB, 788ms
 @category 세그먼트 트리
 @note

 세그먼트 트리로 구간최소, 구간최대 구하기
 최소 세그먼트 트리, 최대 세그먼트 트리를 만들어서
 구간최소, 구간최대를 출력합니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G1_2357 {
    static int N, M, height, size;
    static int[] minArr, maxArr;
    static int minValue, maxValue;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        height = (int)Math.ceil(Math.log(N) / Math.log(2));
        size = (int)Math.pow(2, height + 1);
        minArr = new int[size];
        maxArr = new int[size];

        for(int i = size / 2; i < size / 2 + N; i++){
            int temp = Integer.parseInt(br.readLine());
            minArr[i] = temp;
            maxArr[i] = temp;
        }

        for(int i = size / 2 - 1; i >= 1; i--){
            if (minArr[i * 2] == 0 && minArr[i * 2 + 1] == 0) {
                minArr[i] = 0;
            } else if(minArr[i * 2] == 0){
                minArr[i] = minArr[i * 2 + 1];
            } else if (minArr[i * 2 + 1] == 0) {
                minArr[i] = minArr[i * 2];
            } else {
                minArr[i] = Math.min(minArr[i * 2], minArr[i * 2 + 1]);
            }

            if(maxArr[i * 2] == 0 && minArr[i * 2 + 1] == 0){
                maxArr[i] = 0;
            } else if(maxArr[i * 2] == 0){
                maxArr[i] = maxArr[i * 2 + 1];
            } else if(maxArr[i * 2 + 1] == 0){
                maxArr[i] = maxArr[i * 2];
            } else {
                maxArr[i] = Math.max(maxArr[i * 2], maxArr[i * 2 + 1]);
            }
        }

        // 최소 및 최대 세그먼트 트리 초기화 완료
        // 개선해볼점 : 리프노드는 같이쓰고 이외노드는 따로쓴다면?
        // 배열의 노드들을 class로 만든다면?

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            minSeg(a, b);
            maxSeg(a, b);

            bw.write(minValue + " " + maxValue + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void minSeg(int start, int end){
        minValue = Integer.MAX_VALUE;
        start += size / 2 - 1;
        end += size / 2 - 1;

        while(start <= end){
            if(start % 2 == 1){
                if(minArr[start] != 0 && minArr[start] < minValue){
                    minValue = minArr[start];
                }
                start = (start + 1) / 2;
            } else {
                start /= 2;
            }
            if(end % 2 == 0){
                if(minArr[end] != 0 && minArr[end] < minValue){
                    minValue = minArr[end];
                }
                end = (end - 1) / 2;
            } else {
                end /= 2;
            }
        }
    }

    public static void maxSeg(int start, int end){
        maxValue = 0;
        start += size / 2 - 1;
        end += size / 2 - 1;

        while(start <= end){
            if(start % 2 == 1){
                if(maxArr[start] != 0 && maxArr[start] > maxValue){
                    maxValue = maxArr[start];
                }
                start = (start + 1) / 2;
            } else {
                start /= 2;
            }
            if(end % 2 == 0){
                if(maxArr[end] != 0 && maxArr[end] > maxValue){
                    maxValue = maxArr[end];
                }
                end = (end - 1) / 2;
            } else {
                end /= 2;
            }
        }
    }


}
