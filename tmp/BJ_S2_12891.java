/**

 @author 한규준
 @since 2023-08-03
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_12891.java
 @youtube
 @performance 50088KB, 420ms
 @category 슬라이딩 윈도우
 @note

 먼저 처음의 케이스를 처리한 뒤
 이후 한 문자씩 받으면서 ACGT값을 조절/비교, count값을 조절하면서
 ACGT가 아닌 문자가 나오면 그 다음부터 다시 탐색하면 됩니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

public class BJ_S2_12891 {
	static char[] pwdArr;
	static Map<Character, Integer> acgt = new HashMap<>();
	static Map<Character, Integer> testAcgt = new HashMap<>();
	static int count = 0;
	static char[] chr = {'A', 'C', 'G', 'T'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] temp = br.readLine().split(" ");
		int S = Integer.parseInt(temp[0]), P = Integer.parseInt(temp[1]);
		// 문자열 받기
		pwdArr = br.readLine().toCharArray();
		// 필요 개수 받기
		temp = br.readLine().split(" ");
		int start = 0, end = P;

		// 필요 ACGT개수 넣기
		for(int i = 0; i < 4; i++){
			acgt.put(chr[i], Integer.parseInt(temp[i]));
		}

		// 처음 ACGT개수 값 초기화
		// map자료구조 사용으로 인해 contain key를 사용하려면 초기화해줘야합니다.
		for(int i = 0; i < 4; i++){
			testAcgt.put(chr[i], 0);
		}

		// 문자열 끝에 갈 때까지 탐색
		while(end <= S){
			int flag = 0;

			// 해당 문자열/문자 구간에 대해서 탐색합니다.
			for(int i = start; i < end; i++){

				// 받은 문자가 ACGT중 하나면 값 + 1
				if(testAcgt.containsKey(pwdArr[i])){
					testAcgt.put(pwdArr[i], testAcgt.get(pwdArr[i]) + 1);
					
					// 아니면 전체 값 초기화 후 슬라이드를 통째로 옮깁니다.
					// 이때, 옮긴 슬라이드가 문자열 끝을 넘어가면 종료
					// 그렇지 않으면 ACGT가 아닌 문자 다음부터 다시 검사합니다.
				} else {
					for(int j = 0; j < 4; j++){
						testAcgt.put(pwdArr[j], 0);
					}
					if (start + P < end) {
						end = S + 1;
						flag = 1;
						break;
					} else {
						start = end;
						end++;
					}
					continue;
				}
			}

			// ACGT가 필요한 만큼 들어갔는지 검사합니다.
			for(int i = 0; i < 4; i++){
				if(testAcgt.get(chr[i]) < acgt.get(chr[i])){
					flag = 1;
					break;
				}
			}

			// 이상 없으면 카운트 추가
			// 현재 문자열의 처음 값에 해당하는 ACGT개수를 빼줍니다.
			// 한 칸 전진해 다시 확인합니다.
			if(flag == 0) count++;
			if(testAcgt.get(pwdArr[end - P]) != 0) testAcgt.put(pwdArr[end - P], testAcgt.get(pwdArr[end - P]) - 1);
			start = end;
			end++;
		}
		
		System.out.println(count);
		
	}
}
