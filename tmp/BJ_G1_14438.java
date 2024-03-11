/**

 @author 한규준
 @since 2023-08-28
 @see https://www.acmicpc.net/problem/14438
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_14438.java
 @youtube
 @performance 71448KB, 912ms
 @category 세그먼트 트리
 @note

1. 세그먼트 트리를 구현합니다.
2. 최소값 세그먼트 트리이기 때문에, 해당 부분은 유념합니다.
3. i < j이기 때문에, 그냥 그대로 구현합니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G1_14438 {
	static int N, M, height, start;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		height = (int)Math.ceil(Math.log(N) / Math.log(2));
		start = (int)Math.pow(2, height);
		arr = new int[start * 2];
		Arrays.fill(arr, Integer.MAX_VALUE);
		
		st = new StringTokenizer(br.readLine());
		for(int i = start; i < start + N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = start - 1; i > 0; i--) {
			arr[i] = Math.min(arr[i * 2], arr[i * 2 + 1]);
		}
		
		M = Integer.parseInt(br.readLine());
		
		int a, b, c;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(a == 1) update(b, c);
			else bw.write(getSum(b, c) + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	private static void update(int idx, int val) {
		idx += start - 1;
		arr[idx] = val;
		for(int i = idx /= 2; i > 0; i /= 2) {
			arr[i] = Math.min(arr[i * 2], arr[i * 2 + 1]);
		}
	}
	
	private static int getSum(int left, int right) {
		left += start - 1;
		right += start - 1;
		int result = Integer.MAX_VALUE;
		while(left <= right) {
			if(left % 2 == 1) result = Math.min(result, arr[left++]);
			if(right % 2 == 0) result = Math.min(result, arr[right--]);
			left /= 2;
			right /= 2;
		}
		return result;
	}
}
