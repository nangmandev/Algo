/**

 @author 한규준
 @since 2023-08-18
 @see https://www.acmicpc.net/problem/4256
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_4256.java
 @youtube
 @performance 39272KB, 320ms
 @category 트리 순회
 @note

트리의 전위순회 순서는 각 부분트리의 정점이다(정점 - 왼 - 오)
 중위순회를 보면, 전위순회에서 나온 순서대로 노드를 갈라보면 부분트리로 나눌 수 있다.
 전위순회 index를 하나씩 증가시켜 빼내면서 이를 midIndex로 삼아 왼-오 부분트리로 탐색한다.
 midIndex는 후위순회이고 부분트리의 루트노드이므로 가장 후순위에 출력한다.
 왼-오 부분트리를 같은 방식으로 순회한다.
 끝단 처리를 잘 해주면 완성(start - end가 같은 경우, mid-start가 같은 경우, mid-end가 같은 경우)

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G2_4256 {
	static StringBuilder sb;
	
	static int N;
	static int idx;
	
	static int[] preOrder, inOrder;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			idx = 0;
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			sb = new StringBuilder();
			preOrder = new int[N];
			for(int i = 0; i < N; i++) {
				preOrder[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			inOrder = new int[N];
			for(int i = 0; i < N; i++) {
				inOrder[i] = Integer.parseInt(st.nextToken());
			}
			
			// 초기화 끝
			// 시작과 끝(start, end) index를 매개변수로 던진다.
			// postOrder에서는 start, end에서 preOrder에서 뽑은 수를 찾는다.
			postOrder(0, N - 1);
			
			System.out.println(sb);
		}
	}
	
	public static void postOrder(int start, int end) {
		// start, end를 받았으니, 해당 범위 내에서 preOrder에서 뽑아낸 수를 찾는다.
		// 현재 preorder에 해당하는 인덱스를 찾고, 임시로 저장한다.
		int nowMid = 0;
		for(int i = start; i <= end; i++){
			if(inOrder[i] == preOrder[idx]){
				nowMid = i;
				break;
			}
		}
		idx += 1;
		// start, end범위가 같으면 리프노드이므로 출력하고 탐색을 끝낸다.
		if(start == end) {
			sb.append(inOrder[nowMid]).append(" ");
			return;
		}
		// 찾은곳과 start범위가 같으면 왼쪽노드는 하나이므로 모든 탐색 이후 출력처리
		// 그렇지 않으면 탐색하러 가야 한다.
		if(nowMid != start) postOrder(start, nowMid - 1);
		// 찾은곳과 end범위가 같으면 우측노드는 하나이므로 모든 탐색 이후 출력처리
		// 그렇지 않으면 탐색하러 가야 한다.
		if(nowMid != end) postOrder(nowMid + 1, end);

		// 탐색이 끝나면 지금 nowMid를 출력한다.
		sb.append(inOrder[nowMid]).append(" ");
	}
}
