/**

 @author 한규준
 @since 2023-08-16
 @see https://www.acmicpc.net/problem/1992
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_1992.java
 @youtube
 @performance 12984KB, 92ms
 @category 분할정복, 재귀
 @note

1. 사분면으로 나눈다.
2. 0과 1이 같이 있을 때와 아닐때를 분리
3. 괄호를 먼저 만들고 재귀, 다시 괄호를 닫고 반환

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_S1_1992 {
	static int[][] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			for(int j = 0; j < N; j++) {
				arr[i][j] = temp.charAt(j) - '0';
			}
		}
		
		String result = recur(N, 0, 0);
		
		System.out.println(result);
	}
	
	public static String recur(int N, int y, int x) {
		if(N == 0) return "";
		StringBuilder sb = new StringBuilder();
		
		//System.out.println(N + " " + y + " " + x);
		
		int findZero = 0;
		int findOne = 0;
		
		for(int i = y; i < y + N; i++) {
			for(int j = x; j < x + N; j++) {
				if(arr[i][j] == 0) findZero = 1;
				if(arr[i][j] == 1) findOne = 1;
			}
		}
		
		if(findZero != findOne) {
			if(findZero == 1) return "0";
			else return "1";
		}
		else {
			sb.append("(");
			// 2사분면
			sb.append(recur(N / 2, y, x));
			// 1사분면
			sb.append(recur(N / 2, y, x + N / 2));
			// 3사분면
			sb.append(recur(N / 2, y + N / 2, x));
			// 4사분면
			sb.append(recur(N / 2, y + N / 2, x + N / 2));
			
			sb.append(")");
			return sb.toString();
		}
	}
}
