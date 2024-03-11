/**

 @author 한규준
 @since 2023-08-07
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_2805.java
 @youtube
 @performance 185828KB, 628ms
 @category 이분탐색
 @note
 
 이분탐색을 진행하면 되는데
 1. 적어도 M보다 전체 나무 길이가 길어야 하는
 2. mid의 최대값을 찾아야 한다.
 3. 나무 길이 최대값이 20억이므로 int, long 잘 선택

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_S2_2805 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]), M = Integer.parseInt(temp[1]);
		int[] arr = new int[N];
		long high = 0;
		temp = br.readLine().split(" ");
		for(int i = 0;i < N; i++) {
			arr[i] = Integer.parseInt(temp[i]);
			if(arr[i] > high) high = arr[i];
		}
		
		long low = 0;
		long mid = 0;
		long tempSum = 0;
		
		while(high > low) {
			mid = (high + low) / 2;
			tempSum = 0;
			for(int i = 0; i < N; i++) {
				if(arr[i] - mid > 0) tempSum += arr[i] - mid;
			}
			
			// 최소 M미터를 집에 가져가야하는데
			// 가장 큰 mid를 구하시오
			// M미터보다 크면 mid의 범위를 올려서 확인
			// M미터보다 작으면 high의 범위를 내려서 확인
			// M미터와 같으면? mid범위 올려본다.
			// 같은데 + 1하면 범위 건너뛸수도 있다.
			
			if(tempSum >= M) {
				if(high - 1 == mid && low == mid) break;
				low = mid;
			} else {
				if(high - 1 == mid && low == mid) break;
				high = mid;
			}
		}
		System.out.println(mid);
		bw.flush();
		bw.close();
	}
}
