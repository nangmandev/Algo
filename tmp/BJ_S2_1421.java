/**

 @author 한규준
 @since 2023-08-07
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_1421.java
 @youtube
 @performance
 @category 구현, 케이스나누기
 @note

문제 잘 이해할것

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_S2_1421 {
	static long maxValue;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]), C = Integer.parseInt(temp[1]), W = Integer.parseInt(temp[2]);
		long[] arr = new long[N];
		long woodLen = 1;
		long tempSum = 0;
		long tempValue = 0;
		long maxNum = 0;

		for(int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(br.readLine());
			if(arr[i] > maxNum) maxNum = arr[i];
		}

		// arr[i] / woodLen : 나무의 개수
		// woodLen : 길이
		// W : 단위당 금액

		while(true){
			tempSum = 0;
			if(woodLen > maxNum) break;
			for(int i = 0; i < N; i++){
				tempValue = 0;
				if(arr[i] % woodLen == 0) tempValue += (arr[i] / woodLen) * woodLen * W - ((arr[i] / woodLen - 1) * C);
				else tempValue += (arr[i] / woodLen) * woodLen * W - ((arr[i] / woodLen) * C);
				if(tempValue > 0) tempSum += tempValue;
			}
			if(tempSum > maxValue){
				maxValue = tempSum;
			}
			woodLen++;
		}

		System.out.println(maxValue);
	}
}
