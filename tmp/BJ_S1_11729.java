/**

 @author 한규준
 @since 2023-08-11
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_11729.java
 @youtube
 @performance 48932KB, 300ms
 @category 재귀
 @note

자기자신이 움직이기 전에 자기자신보다 작은 원판 움직이기
그 다음 자기자신을 움직이고, 남은 원판 데려오기

 */

package algo;

import java.io.*;
import java.util.TreeMap;

public class BJ_S1_11729 {
	private static int count = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= N; i++) {
			count =  count * 2 + 1;
		}
		
		bw.write(count + "\n");

		// 시작하는 기둥, 중간 기둥, 옮겨야 할 기둥, 원판개수
		hanoi(1, 2, 3, N, bw);
		
		bw.flush();
		bw.close();
	}
	
	private static void hanoi(int from, int m, int to, int number, BufferedWriter bw) throws Exception{
		// 움직여야 할 원판이 없으면 종료.
		// number가 클수록 원판 크기가 큰것(밑에있는것)
		if(number == 0) return;

		// 자기자신이 움직이기 전에 자기자신보다 작은걸 목적지가 아닌 기둥으로 옮겨야 한다.
		hanoi(from, to, m, number - 1, bw);
		// 재귀함수이기 때문에, 끝까지 갔다올것

		// 다른 원판들이 움직이고 나서 자신도 움직인다.
		// 자신보다 작은 원판들은 목적지가 아닌 곳에 가 있다.
		bw.write(from + " " + to + "\n");

		// 나머지 원판들을 데려온다.
		hanoi(m, from, to, number - 1, bw);

		// 재귀적으로 수행되는데, 기둥을 보면
		// 자기자신보다 작은것 from -> m, 자기자신 이동, 자기자신보다 작은것 데려오기로 구성
	}
}
