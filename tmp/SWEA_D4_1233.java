/**

 @author 한규준
 @since 2023-08-08
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D4_1233.java
 @youtube
 @performance 22012KB, 120ms
 @category 문제읽기, 트리성질
 @note

완전 이진 트리에서 리프노드의 개수는 N / 2
따라서, 1 ~ N / 2까지는 연산자, N / 2 + 1 ~ N까지는 피연산자여야 합니다.
노드 개수가 3개 미만인 경우 처리를 따로 해줍니다.

 */


package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_D4_1233 {
	private static boolean TF = true;
	private static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] temp;
		
		for(int tc = 1; tc <= 10; tc++) {
			N = Integer.parseInt(br.readLine());
			TF = true;
			if(N < 3) {
				TF = false;
			}
			
			for(int i = 1; i <= N / 2; i++) {
				temp = br.readLine().split(" ");
				if(temp[1].equals("*")
				|| temp[1].equals("/")
				|| temp[1].equals("+")
				|| temp[1].equals("-")) {
					continue;
				} else {
					TF = false;
				}
			}
			
			for(int i = N / 2 + 1; i <= N; i++) {
				temp = br.readLine().split(" ");
				if(temp[1].equals("*")
				|| temp[1].equals("/")
				|| temp[1].equals("+")
				|| temp[1].equals("-")) {
					TF = false;
				} else continue;
			}
			if(TF == true) bw.write("#" + tc + " 1" + "\n");
			else bw.write("#" + tc + " 0" + "\n");
		}
		
		bw.flush();
		bw.close();
	}

}
