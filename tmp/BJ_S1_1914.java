/**

 @author 한규준
 @since 2023-08-07
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_1914.java
 @youtube
 @performance
 @category 재귀
 @note

 재귀, 매우 큰 수 처리
 

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class BJ_S1_1914 {
	private static BigInteger count = new BigInteger("0");
	private static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		for(int i = 1; i <= N; i++){
			count = count.multiply(new BigInteger("2"));
			count = count.add(new BigInteger("1"));
		}
		bw.write(count + "\n");
		// 시작하는 기둥, 중간 기둥, 옮겨야 할 기둥, 원판개수
		if(N <= 20) {
			hanoi(1, 2, 3, N, bw);
		}

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
