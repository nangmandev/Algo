/**

 @author 한규준
 @since 2023-08-23
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D4_3289.java
 @youtube
 @performance 98808kb, 420ms
 @category Union-Find
 @note

Union-Find연산을 그대로 진행하면 됩니다.
1. testCase, 집합 개수, 명령 개수를 받아 초기화합니다.
2. 명령, 집합 a, 집합 b를 받습니다.
3. 명령이 0이면 집합 a와 집합b를 합칩니다. -> 배열이 가리키는 인덱스를 루트노드로 삼습니다.
4. 명령이 1이면 집합 a와 집합b의 부모노드가 같은지 확인해서 같은 집합인지를 확인합니다.


 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_D4_3289 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[n + 1];
			for(int i = 1; i <= n; i++) {
				arr[i] = i;
			}
			// 초기화 완료
			
			bw.write("#" + tc + " ");
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int inst = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(inst == 0) union(arr, a, b);
				else {
					int pa = find(arr, a);
					int pb = find(arr, b);
					if(pa == pb) bw.write("1");
					else bw.write("0");
				}
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	public static int find(int[] arr, int a) {
		if(arr[a] == a) return a;
		return arr[a] = find(arr, arr[a]);
	}
	
	public static void union(int[] arr, int a, int b) {
		int parentA = find(arr, a);
		int parentB = find(arr, b);
		
		if(parentA == parentB) return;
		else arr[parentB] = parentA;
	}
}
