/**

 @author 한규준
 @since 2023-08-28
 @see https://www.acmicpc.net/problem/1275
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_1275.java
 @youtube
 @performance 96184KB, 1112ms
 @category 세그먼트 트리
 @note

1. 세그먼트 트리를 그대로 구현
2. x > y인경우 y~x까지를 구하는 것으로 바꾼다.
3. 이외는 동등

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G1_1275 {
	static int N, Q, height, start;
	static long[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		height = (int)Math.ceil(Math.log(N) / Math.log(2));
		start = (int)Math.pow(2, height);
		arr = new long[start * 2];
		
		st = new StringTokenizer(br.readLine());
		for(int i = start; i < start + N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = start - 1; i > 0; i--) {
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
		}
		
		int x, y, a, b;
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if(x > y) bw.write(getSum(y, x) + "\n"); 
			else bw.write(getSum(x, y) + "\n");
			update(a, b);
		}
		
		bw.flush();
		bw.close();
	}
	
	private static void update(int idx, int val) {
		idx += start - 1;
		arr[idx] = val;
		for(int i = idx / 2; i > 0; i /= 2) {
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
		}
	}
	
	private static long getSum(int left, int right) {
		left += start - 1;
		right += start - 1;
		long result = 0;
		while(left <= right) {
			if(left % 2 == 1) result += arr[left++];
			if(right % 2 == 0) result += arr[right--];
			left /= 2;
			right /= 2;
		}
		return result;
	}
}
