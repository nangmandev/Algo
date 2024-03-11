/**

@author 한규준
@since 2023-08-04
@see
@git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D4_1218.java
@youtube
@performance 18396kb, 106ms
@category 스택, 문자열
@note

여는 괄호면 스택에 넣고
닫는 괄호면 스택에서 뺍니다.
예외케이스를 다룹니다.
1. 여는 괄호만 있어서 테케를 전부 통과한 경우
2. 시작하자마자 빼는경우
3. 중간에 사이즈가 0인데 빼는경우
4. 끝났는데 스택에 문자가 남아있는경우

*/

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class SWEA_D4_1218 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Character> deq = new ArrayDeque<>();
		String temp;
		int len;
		
		for(int i = 1; i <= 10; i++) {
			len = Integer.parseInt(br.readLine());
			temp = br.readLine();
			boolean flag = true;
			
			for(int j = 0; j < len; j++) {
				char getChr = temp.charAt(j);
				if(getChr == '(' || getChr == '[' || getChr == '{' || getChr == '<') {
					deq.push(getChr);
				} else {
					if(deq.size() == 0) {
						flag = false;
						break;
					}
					char stackChar = deq.pop();
					if(getChr == ')' && stackChar == '(') {
						continue;
					} else if(getChr == ']' && stackChar == '[') {
						continue;
					} else if(getChr == '}' && stackChar == '{') {
						continue;
					} else if(getChr == '>' && stackChar == '<') {
						continue;
					} else {
						flag = false;
						break;
					}
				}
			}
			
			if(flag == false || deq.size() != 0) System.out.printf("#" + i + " " + 0 + "\n");
			else System.out.printf("#" + i + " " + 1 + "\n");
			deq.clear();
		}
	}
}
