/**

 @author 한규준
 @since 2023-08-28
 @see https://www.acmicpc.net/problem/2268
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_2268.java
 @youtube
 @performance 315868Kb, 1212ms
 @category 세그먼트 트리
 @note

1. 세그먼트 트리를 구현한다.
2. 이때, i > j, j > i인 경우를 고려한다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G1_2268 {
	static int N, M, height, start;
	static long[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		height = (int)Math.ceil(Math.log(N) / Math.log(2));
		start = (int)Math.pow(2, height);
		arr = new long[start * 2];
		
		int a, b, c;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(a == 0) {
				if(b > c) bw.write(getSum(c, b) + "\n");
				else bw.write(getSum(b, c) + "\n");
			}
			else update(b, c);
		}
		
		bw.flush();
		bw.close();
	}
	
	static void update(int idx, int val) {
		idx += start - 1;
		arr[idx] = val;
		for(int i = idx / 2; i > 0; i /= 2) {
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
		}
	}
	
	static long getSum(int left, int right) {
		left += start - 1;
		right += start - 1;
		long ret = 0;
		while(left <= right) {
			if(left % 2 == 1) ret += arr[left++];
			if(right % 2 == 0) ret += arr[right--];
			left /= 2;
			right /= 2;
		}
		return ret;
	}
}
