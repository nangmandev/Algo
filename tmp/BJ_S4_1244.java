/**

@author 한규준
@since 2023-08-01
@see
@git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S4_1244.java
@youtube
@performance 11816KB, 76ms
@category #구현, 시뮬레이션, 문제읽기
@note

1. 스위치 개수가 주어짐 <= 100
2. 각 스위치의 상태가 주어짐
3. 학생수가 주어짐. <= 100
4. 넷째부터 마지막까지 한 학생의 성별, 학생이 받은 수가 주어짐

남학생이면 자기가 받은 수의 배수 상태를 바꿈
여학생은 자기 주변으로 대칭인 구간을 싹다바꿈
남학생 1
여학생 2
켜짐 1
꺼짐 0

1. 스위치 받음
2. 스위치 상태 초기화
3. 학생수 받음
4. 학생 받으면서 스위치 상태를 바꿈
4-1. 남학생이면 배수 거꾸로
4-2. 여학생이면 대칭 찾기

스위치 100개 이하
학생수 100명 이하
전부다 여학생이고 전부다 수정해야한다면?
100 * 50 -> 5000번 탐색
시간O

*/

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_S4_1244 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int switchN = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		int[] arr = new int[str.length + 1];
		
		for(int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(str[i - 1]);
		}
		// arr[1] ~ arr[arr.length] 까지 초기화
		
		int studentN = Integer.parseInt(br.readLine());
		for(int i = 0; i < studentN; i++) {
			// 학생 받아오기
			String[] temp = br.readLine().split(" ");
			int a = Integer.parseInt(temp[0]), b = Integer.parseInt(temp[1]);
			
			// 남자면 배수 뒤집기
			if(a == 1) {
				for(int j = b; j < arr.length; j += b) {
					if(arr[j] == 1) arr[j] = 0;
					else arr[j] = 1;
				}
				
				// 여자면 대칭 뒤집기
			} else {
				if(arr[b] == 1) arr[b] = 0;
				else arr[b] = 1;
				int j = 1;
				while(true) {
					if(b - j < 1 || b + j > arr.length - 1) break;
					else {
						if(arr[b - j] == arr[b + j] && arr[b - j] == 1) {
							arr[b - j] = 0;
							arr[b + j] = 0;
						} else if(arr[b - j] == arr[b + j] && arr[b - j] == 0) {
							arr[b - j] = 1;
							arr[b + j] = 1;
						} else break;
					}
					j++;
				}
			}
		}
		
		
		// 출력 한번에
		for(int i = 1; i < arr.length; i++) {
			if(i % 20 == 0) bw.write(arr[i] + "\n");
			else bw.write(arr[i] + " ");
		}
		
		bw.flush();
		bw.close();
	}
}
