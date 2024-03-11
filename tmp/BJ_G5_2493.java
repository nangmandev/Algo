/**
 * @author 한규준
 * @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_2493.java
 * @youtube
 * @performance 167608KB, 868ms
 * @category 스택
 * @note // 6 9 5 7 4인 탑
 * // 좌측으로 레이저 발사
 * // 4에서 발사한건 7에
 * // 7에서 발사한건 9에
 * // 5에서 발사한건 9에
 * // 9에서 발사한건 수신 X
 * // 6에서 발사한건 수신 X
 * <p>
 * // 탑 개수 1 <= N <= 500000
 * // 탑 높이 1 <= 높이 <= 100000000
 * <p>
 * // 끝에서부터 데리고와야함
 * // 끝에서부터 스택에 넣고, top보다 작으면 넣고 크거나 같으면 뺀다
 * // 뺄때마다 결과 배열에 몇번째인지 입력
 * @see
 * @since 2023-08-01
 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Stack;

public class BJ_G5_2493 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		
		int N = Integer.parseInt(br.readLine());
		String[] arr = br.readLine().split(" ");
		int[] top = new int[N];
		int[] result = new int[N];
		int index = N - 2;
		
		for(int i = 0; i < N; i++) {
			top[i] = Integer.parseInt(arr[i]);
		}
		// 배열 0은 탑크기, 1은 인덱스, 2는 수신한곳 인덱스
		Stack<Integer> stack = new Stack<>();
		stack.push(N - 1);
		
		while(!stack.isEmpty() && index >= 0) {
			// 스택탑이 지금값보다 크면
			if(top[stack.lastElement()] > top[index]) {
				stack.push(index--);
				// 지금값이 스택탑보다 크면
				// 스택 비거나 지금값보다 클때까지 내려가기
			} else {
				result[stack.pop()] = index + 1;
				if(stack.isEmpty() && index != 0) stack.push(index--);
			}
		}
		
		for(int i = 0; i < N; i++) {
			bw.write(result[i] + " ");
		}
		
		bw.flush();
		bw.close();
	}
}
