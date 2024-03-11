/**

 @author 한규준
 @since 2023-08-22
 @see https://www.acmicpc.net/problem/11505
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_11505.java
 @youtube
 @performance 94252KB, 472ms
 @category 세그먼트 트리
 @note

세그먼트 트리를 이용한 구간합 구하기
오버플로우에 유의

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G1_11505 {
	static int N, M, K;
	static int height, size;
	static long arr[];
	static final int MOD = 1000000007;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		height = (int)Math.ceil(Math.log(N) / Math.log(2));
		size = (int)Math.pow(2, height + 1);
		arr = new long[size + 1];
		Arrays.fill(arr, 1);
		
		for(int i = size / 2; i < size / 2 + N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 초기화 끝
		for(int i = size / 2 - 1; i >= 1; i--) {
			arr[i] = ((arr[i * 2] % MOD) * (arr[i * 2 + 1] % MOD)) % MOD;
		}
		
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a == 1) update(b, c);
			else bw.write(find(b, c) + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	private static long find(int start, int end){
		start += size / 2 - 1;
		end += size / 2 - 1;
		long result = 1;
		while(start <= end) {
			if(start % 2 == 0) {
				start /= 2;
			}
			else {
				result *= (arr[start] % MOD);
				result = result % MOD;
				start = (start + 1) / 2;
			}
			if(end % 2 == 0) {
				result *= (arr[end] % MOD);
				result = result % MOD;
				end = (end - 1) / 2;
			}
			else {
				end /= 2;
			}
		}
		return result;
	}
	
	private static void update(int index, int num) {
		index += size / 2 - 1;
		arr[index] = num;
		while(index != 1) {
			int tempidx = index / 2;
			arr[tempidx] = ((arr[tempidx * 2] % MOD) * (arr[tempidx * 2 + 1] % MOD)) % MOD;
			index /= 2;
		}
	}
}
