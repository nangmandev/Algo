/**

 @author 한규준
 @since 2023-08-28
 @see https://www.acmicpc.net/problem/14427
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_14427.java
 @youtube
 @performance 66352KB, 788ms
 @category 세그먼트 트리
 @note

1. 세그먼트 트리를 구현합니다.
2. 최소값 세그먼트 트리이자, 인덱스를 사용해야 합니다.
3. 인덱스 계산을 유념하여 구현합니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G1_14427 {
	static int N, M, height, start;
	static int[] arr, valueArr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		height = (int)Math.ceil(Math.log(N) / Math.log(2));
		start = (int)Math.pow(2, height);
		valueArr = new int[start + 1];
		arr = new int[start * 2];
		Arrays.fill(valueArr, Integer.MAX_VALUE);
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			valueArr[i] = Integer.parseInt(st.nextToken());
			arr[i + start - 1] = i;
		}
		
		for(int i = start - 1; i > 0; i--) {
			if(valueArr[arr[i * 2]] <= valueArr[arr[i * 2 + 1]]) {
				arr[i] = arr[i * 2];
			}
			else {
				arr[i] = arr[i * 2 + 1];
			}
		}
		
		M = Integer.parseInt(br.readLine());
		int a, b, c;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			if(a == 1) {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				update(b, c);
			}
			else bw.write(arr[1] + "\n");
		}
		bw.flush();
		bw.close();
	}
	
	private static void update(int idx, int val) {
		valueArr[idx] = val;
		idx += start - 1;
		for(int i = idx / 2; i > 0; i /= 2) {
			if(valueArr[arr[i * 2]] <= valueArr[arr[i * 2 + 1]]) {
				arr[i] = arr[i * 2];
			}
			else {
				arr[i] = arr[i * 2 + 1];
			}
		}
	}
}
