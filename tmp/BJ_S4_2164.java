/**

 @author 한규준
 @since 2023-08-04
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S4_2164.java
 @youtube
 @performance 24960KB, 160ms
 @category 큐
 @note

 단순 큐 구현입니다.
 큐 맨 앞의 숫자를 빼서 제거하고
 다시 한 번 큐 맨 앞의 숫자를 빼서 뒤에 추가합니다.
 한 장이 남게 되면 반복문이 종료됩니다.
 두 장일 때, 한 장을 버리고 한 장인 상태인데
 이 때에도 큐에서 한 장을 빼고 다시 집어넣기 때문에
 변화가 없습니다.
 -> 따로 예외처리할 사항은 없습니다.

 */

package algo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BJ_S4_2164 {
	public static void main(String[] args) throws Exception{
		Deque<Integer> deq = new ArrayDeque<Integer>();
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		for(int i = 0; i < N; i++) {
			deq.add(i + 1);
		}
		
		while(deq.size() != 1) {
			deq.pollFirst();
			deq.add(deq.pollFirst());
		}
		
		System.out.println(deq.pollFirst());
	}
}