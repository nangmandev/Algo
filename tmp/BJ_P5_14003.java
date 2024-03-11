/**

 @author 한규준
 @since 2023-09-11
 @see https://www.acmicpc.net/problem/14003
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_P5_14003.java
 @youtube
 @performance 309292KB, 1004ms
 @category DP, 이분탐색
 @note

hint : index

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_P5_14003 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[][] arr = new int[N + 1][2];
		int[][] dp = new int[N + 1][2];
		
		for(int i = 1; i <= N; i++) {
			arr[i][0] = Integer.parseInt(st.nextToken());
		}
		
		int dpIdx = 2;
		int maxIdx = 1;
		dp[1][0] = arr[1][0];
		dp[1][1] = 1;
		for(int i = 2; i <= N; i++) {
			if(arr[i][0] > dp[dpIdx - 1][0]) {
				dp[dpIdx][0] = arr[i][0];
				dp[dpIdx][1] = i;
				arr[i][1] = dp[dpIdx - 1][1];
				maxIdx = i;
				dpIdx++;
			}
			else {
				int left = 1;
				int right = dpIdx - 1;
				while(left < right) {
					int mid = (left + right) / 2;
					if(arr[i][0] > dp[mid][0]) {
						left = mid + 1;
					}
					else {
						right = mid;
					}
				}
				dp[left][0] = arr[i][0];
				dp[left][1] = i;
				arr[i][1] = dp[left - 1][1];
			}
		}
		
		Stack<Integer> stack = new Stack<>();
		while(maxIdx != 0){
			stack.push(arr[maxIdx][0]);
			maxIdx = arr[maxIdx][1];
		}

		System.out.println(dpIdx - 1);
		while(!stack.isEmpty()) {
			bw.write(stack.pop() + " ");
		}

		bw.flush();
		bw.close();
	}
}
