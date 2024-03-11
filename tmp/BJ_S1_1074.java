/**

 @author 한규준
 @since 2023-08-14
 @see https://www.acmicpc.net/problem/1074
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_1074.java
 @youtube
 @performance 11620KB, 84ms
 @category 분할정복, 재귀
 @note

첫번쨰 생각했던 방법
1. 재귀는 사용한다.
2. Z방향으로 다음 순서를 탐색하면서 카운트를 늘린다.(재귀 호출 순서 -> 왼위 오위 왼밑 오밑)
3. N이 0이 되면 끝
그런데 이 방법으로 풀면 최악의 경우 전부 탐색해야 한다.
해당 경우 최악의 경우는 2^15인 32768이고, 2차원 배열이므로 2^30인 10억번 탐색해야 한다.
이 문제의 시간 제한은 0.5초이므로, 10억번 탐색을 하게 되면 무조건 시간 초과가 발생한다. -> 0.5초면 10분의 1인 1억번이어도 시간초과 발생할듯...
또, 재귀를 하므로 스택 호출에도 시간이 걸린다.

두번쨰 생각한 방법
-> 아예 탐색범위를 제외해야 한다.
-> 틀린 범위는 탐색하러 들어가지 않는다.
-> 기존에는 모든 범위를 탐색했지만, 반 혹은 절반씩 줄일 수 있을까?
-> 4 x 4배열을 몇 번 그려보니, 숫자 집합이 4군데로 나뉘어진다.
-> 총 n^2인 배열에서(n은 한 변의 길이)
-> 1사분면은 n * n / 4
-> 2사분면은 0
-> 3사분면은 n * n / 2
-> 4사분면은 n * n / 4 * 3

--> 변경해야 하는 값 : n(또는 한 변의 길이), y좌표, x좌표(최소값을 더하고 초기화)

1. 배열 한 변의 길이가 1이 될 때까지 계속 진행한다.
2. 1사분면이면 총 탐색범위 / 4를 더하고 좌표 초기화
3. 2사분면이면 더하지 않고 좌표 초기화
4. 3사분면이면 총 탐색범위 / 2를 더하고 좌표 초기화
5. 4사분면이면 총 탐색범위 / 4 * 3을 더하고 좌표 초기화
6. 이 과정을 마치면, 한 변의 길이 / 2 또는 n - 1을 넘긴다.
7. 이렇게 되면, 사분면마다 최소값을 계속 더하면서, 마지막까지 진행하게 된다.
8. 결과가 합.


 */

package algo;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class BJ_S1_1074 {
	static int sum = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		recursion(N, r, c);
		
		bw.write(sum + "\n");
		bw.flush();
		bw.close();
	}
	
	public static void recursion(int n, int r, int c) {
		if(n == 0) return;
		
		int len = (int)Math.pow(2,  n);
		int ro = len * (int)Math.pow(2,  n);
		
		// 2사분면
		if(r < len / 2 && c < len / 2) {
			recursion(n - 1, r, c);
		}
		// 3사분면
		else if(r >= len / 2 && c < len / 2) {
			sum = sum + ro / 2;
			recursion(n - 1, r - (len / 2), c);
		}
		// 1사분면
		else if(r < len / 2 && c >= len / 2) {
			sum = sum + ro / 4;
			recursion(n - 1, r, c - (len / 2));
		}
		// 4사분면
		else if(r >= len / 2 && c >= len / 2) {
			sum = sum + ro / 4 * 3;
			recursion(n - 1, r - (len / 2), c - (len / 2));
		}
	}
}
