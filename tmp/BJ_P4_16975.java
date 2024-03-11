/**

 @author 한규준
 @since 2023-08-29
 @see https://www.acmicpc.net/problem/16975
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_P4_16975.java
 @youtube
 @performance 86992KB, 1264ms
 @category 세그먼트 트리, 게으른 전파
 @note

1. 세그먼트트리 구현
2. 게으른 전파 -> get시 주소가 하나

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_P4_16975 {
	static int N, M, height, start;
	static long[] arr, comp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		height = (int)Math.ceil(Math.log(N) / Math.log(2));
		start = (int)Math.pow(2, height);
		arr = new long[start * 2];
		comp = new long[start * 2];
		
		st = new StringTokenizer(br.readLine());
		for(int i = start; i < start + N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = start - 1; i > 0; i--) {
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
		}
		
		M = Integer.parseInt(br.readLine());
		int a, b, c, d;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			if(a == 1) {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				update(b, c, d);
			}
			else {
				b = Integer.parseInt(st.nextToken());
				b += start - 1;
				long result = arr[b];
				while(b != 0) {
					result += comp[b];
					b /= 2;
				}
				bw.write(result + "\n");
			}
		}
		bw.flush();
		bw.close();
	}
	
	private static void update(int left, int right, long val) {
		left += start -1;
		right += start -1;
		long cp = 1;
		while(left <= right) {
			if(left % 2 == 1) updateCompensator(left++, val, cp);
			if(right % 2 == 0) updateCompensator(right--, val, cp);
			left /= 2;
			right /= 2;
			cp *= 2;
		}
	}
	
	private static void updateCompensator(int idx, long val, long cp) {
		comp[idx] += val;
		idx /= 2;
		while(idx != 0) {
			arr[idx] += val * cp;
			idx /= 2;
		}
	}
}