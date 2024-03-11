/**

 @author 한규준
 @since 2023-08-07
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S4_1158.java
 @youtube
 @performance 13184KB, 2008ms
 @category 요세푸스, 큐 없이 풀기
 @note

배열순회
시간 길어짐

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_S4_1158 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]), K = Integer.parseInt(temp[1]);
		
		int[] arr = new int[N];
		int[] resultArr = new int[N];
		int[] visited = new int[N];
		int removeCount = 1;
		int visitedCount = 0;
		int arrIdx = 0;
		
		for(int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}
		
		while(visitedCount < N) {
			// 배열을 돌면서
			// 아직 안빼냈던거고 K번째면
			// 빼내고 방문 체크
			if(visited[arrIdx] == 0 && removeCount == K) {
				resultArr[visitedCount++] = arr[arrIdx];
				visited[arrIdx++] = 1;
				removeCount++;
			}
			// 아직 안뺀건데 K번째가 아니면
			// 카운트 플러스하고 다음걸로
			else if(visited[arrIdx] == 0 && removeCount != K) {
				removeCount++;
				arrIdx++;
			}
			// 방문한거면
			// 다음걸로
			else if(visited[arrIdx] == 1) {
				arrIdx++;
			}
			// 끝에 도착했으면 다시 처음부터
			arrIdx = arrIdx % N;
			if(removeCount == K + 1) {
				removeCount = 1;
			}
			//System.out.println(Arrays.toString(visited));
			//System.out.println(arrIdx + " " + removeCount + " " + visitedCount);
		}
		
		bw.write("<");
		for(int i = 0; i < N - 1; i++) {
			bw.write(resultArr[i] + ", ");
		}
		bw.write(resultArr[N - 1] + ">");
		
		bw.flush();
		bw.close();
	}
}