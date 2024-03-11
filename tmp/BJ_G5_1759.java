/**

 @author 한규준
 @since 2023-08-22
 @see https://www.acmicpc.net/problem/1759
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_1759.java
 @youtube
 @performance 12596KB, 84ms
 @category 조합, 정렬
 @note

정렬을 한 다음
조합을 구하면서 조건 확인

 1. 정렬을 합니다. 정렬을 하지 않으면 사전 순대로 출력되지 않습니다.
 2. 정렬된 상태에서 조합을 구합니다. 순열을 구하면 중복되거나 순서만 바뀐 암호가 나옵니다.
 3. 구해진 조합에서 모음 1개, 자음 2개 이상 조건을 검사합니다.
 4. 맞으면 출력합니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G5_1759 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int L, C;
	static char[] src;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		src = new char[C];
		
		String[] temp = br.readLine().split(" ");
		for(int i = 0; i < C; i++) {
			src[i] = temp[i].charAt(0);
		}
		
		Arrays.sort(src);
		
		DFS(0, 0, new char[L]);

		bw.flush();
		bw.close();
	}
	
	private static void DFS(int nowIdx, int level, char[] tempArr) throws Exception{
		if(level == L) {
			int mo = 0, ja = 0;
			for(int i = 0; i < tempArr.length; i++) {
				if(tempArr[i] == 'a' || tempArr[i] == 'e' || tempArr[i] == 'i'
				|| tempArr[i] == 'o' || tempArr[i] == 'u') {
					mo++;
				}
			}
			ja = L - mo;
			if(mo >= 1 && ja >= 2) {
				for(int i = 0; i < tempArr.length; i++) {
					bw.write(tempArr[i]);
				}
				bw.write("\n");
			}
			return;
		}
		
		for(int i = nowIdx; i < C; i++) {
			tempArr[level] = src[i];
			DFS(i + 1, level + 1, tempArr);
		}
	}
	
}
