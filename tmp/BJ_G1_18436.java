/**

 @author 한규준
 @since 2023-08-28
 @see https://www.acmicpc.net/problem/18436
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_18436.java
 @youtube
 @performance 80220KB, 1028ms
 @category 세그먼트 트리
 @note
 
1. 세그먼트 트리 2개(홀 / 짝) 만들고
2. 따로따로 갱신/출력

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G1_18436 {
	static int N, M, height, start;
	static int[] odd, even, valueArr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		height = (int)Math.ceil(Math.log(N) / Math.log(2));
		start = (int)Math.pow(2, height);
		valueArr = new int[N + 1];
		odd = new int[start * 2];
		even = new int[start * 2];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			valueArr[i] = Integer.parseInt(st.nextToken());
			if(valueArr[i] % 2 == 0) {
				even[i + start - 1] = 1;
			}
			else odd[i + start - 1] = 1;
		}
		
		for(int i = start - 1; i > 0; i--) {
			even[i] = even[i * 2] + even[i * 2 + 1];
			odd[i] = odd[i * 2] + odd[i * 2 + 1];
		}
		
		// 초기화 완료
		M = Integer.parseInt(br.readLine());
		int a, b, c;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(a == 1) {
				update(b, c);
				continue;
			}
			else if(a == 2) {
				bw.write(getEven(b, c) + "\n");
				continue;
			}
			else if(a == 3) {
				bw.write(getOdd(b, c) + "\n");
				continue;
			}
		}
		bw.flush();
		bw.close();
	}
	
	private static void update(int idx, int val) {
		valueArr[idx] = val;
		if(valueArr[idx] % 2 == 0) {
			even[idx + start - 1] = 1;
			odd[idx + start - 1] = 0;
		}
		else {
			even[idx + start - 1] = 0;
			odd[idx + start - 1] = 1;
		}
		
		idx += start - 1;
		
		for(int i = idx / 2; i > 0; i /= 2) {
			even[i] = even[i * 2] + even[i * 2 + 1];
			odd[i] = odd[i * 2] + odd[i * 2 + 1];
		}
	}
	
	private static int getEven(int left, int right) {
		left += start - 1;
		right += start - 1;
		int result = 0;
		while(left <= right) {
			if(left % 2 == 1) result += even[left++];
			if(right % 2 == 0) result += even[right--];
			left /= 2;
			right /= 2;
		}
		return result;
	}
	
	private static int getOdd(int left, int right) {
		left += start - 1;
		right += start - 1;
		int result = 0;
		while(left <= right) {
			if(left % 2 == 1) result += odd[left++];
			if(right % 2 == 0) result += odd[right--];
			left /= 2;
			right /= 2;
		}
		return result;
	}
}
