/**

 @author 한규준
 @since 2023-08-07
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_2559.java
 @youtube
 @performance 23204KB, 1008ms
 @category 수열, 슬라이딩 윈도우
 @note

현재는 슬라이딩윈도우가 아닌데
이후 구현하면 반복횟수 적어짐

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_S3_2559 {
	static int N;
	static int K;
	static int[] arr;
	static int maxValue;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		K = Integer.parseInt(temp[1]);
		arr = new int[N];
		maxValue = Integer.MIN_VALUE;

		temp = br.readLine().split(" ");
		for(int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(temp[i]);
		}

		int start = 0;
		int end = K;

		while(end <= N){
			int tempSum = 0;
			for(int i = start; i < end; i++){
				tempSum += arr[i];
			}
			if(tempSum > maxValue) {
				maxValue = tempSum;
			}
			start++;
			end++;
		}

		System.out.println(maxValue);
	}
}
