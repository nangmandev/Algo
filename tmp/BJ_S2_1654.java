/**

 @author 한규준
 @since 2023-08-03
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_1654.java
 @youtube
 @performance 15524KB, 144ms
 @category 이진탐색 + 주변탐색
 @note
 
 // 이진탐색으로 해결하려했는데 안됨
 // 이진탐색 +1, -1범위 같이 탐색

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_S2_1654 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		int K = Integer.parseInt(str[0]), N = Integer.parseInt(str[1]);
		
		int[] arr = new int[K];
		
		long max = 0, min = 0, mid = 0;
		long sum = 0;
		long result = 0;
		
		for(int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(arr[i] > max) max = arr[i];
		}
		
		while(max >= min) {
			mid = (max + min) / 2;
			if(mid == 0) mid = 1;
			sum = 0;
			for(int i = 0;  i < arr.length; i++) {
				sum += arr[i] / mid;
			}
			if(sum >= N) {
				min = mid + 1;
				if(max == mid || max - 1 == mid) break;
			} else {
				max = mid - 1;
			}
		}
		
		int tempsum = 0;
		for(int l = 0; l < arr.length; l++) {
			tempsum += arr[l] / max;
		}
		int tempsum2 = 0;
		for(int m = 0; m < arr.length; m++) {
			tempsum2 += arr[m] / min;
		}
		if(tempsum >= N) {
			bw.write(max + "\n");
		} else if(sum >= N){
			bw.write(mid + "\n");
		} else if(tempsum2 >= N) {
			bw.write(min + "\n");
		}
		bw.flush();
		bw.close();
	}
}
