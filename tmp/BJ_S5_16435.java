/**

 @author 한규준
 @since 2023-08-14
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S5_16435.java/
 @youtube
 @performance 11856KB, 88ms
 @category 정렬, 구현
 @note

하나씩 진행하면서
먹을 수 있으면 길이 + 1
아니면 종료하고 결과 출력

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_S5_16435 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for(int i = 0; i < N; i++) {
			if(arr[i] <= L) {
				L++;
			} else {
				break;
			}
		}
		
		System.out.println(L);
	}
}
