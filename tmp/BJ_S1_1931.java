/**

 @author 한규준
 @since 2023-08-11
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_1931.java
 @youtube
 @performance 45664KB, 584ms
 @category 그리디
 @note

잘 생각해보기

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_S1_1931 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, (o1, o2) -> o2[1] != o1[1] ? o1[1] - o2[1] : o1[0] - o2[0]);
		
		int endTime = arr[0][1];
		int count = 1;
		
		for(int i = 1; i < N; i++) {
			System.out.println(arr[i][0] + " " + arr[i][1]);
			if(arr[i][0] >= endTime) {
				endTime = arr[i][1];
				count++;
			}
		}
		
		System.out.println(count);
	}
}
