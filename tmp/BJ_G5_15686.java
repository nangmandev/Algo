/**

 @author 한규준
 @since 2023-08-04
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_15686.java
 @youtube
 @performance 18792KB, 332ms
 @category 백트래킹, 조합
 @note

1트 : 인접행렬BFS, 백트래킹 조합으로 탐색 -> 당연히 시간초과
 2트 : 조합 탐색 -> 중복조합이 나와서 시간초과
 3트 : 중복 없는 조합 -> 통과

 1. BFS
 BFS의 경우 인첩행렬로 처리하게 되면 시간복잡도가 O(N^2)입니다.
 재귀로 nCr 조합을 구현한 경우 시간복잡도 O(2^n) -> 다만 이 경우, 모든 r에 대하여 (NP로 구하면 O(N))
 백트래킹으로 n * n행렬에서 x개 중의 m개 조합을 구현한 경우 시간복잡도 O(N^2 * (x - m)) 인데, 상수 지우고 O(N^2)
 따라서, 모든 1을 찾고 -> O(n^2), 조합을 백트래킹으로 구현하고 -> O(N^2), BFS를 하게 되면 -> O(N^2)
 -> O(n^2)들의 향연이라 절대 불가능하다.
 (백트래킹 방식은 연구소 문제에서 사용하는 방식)
 2500 * 2500 * 2500 = 156억...

 2. 조합(재귀)
 조합을 재귀로 구하면 O(N^2) -> 이 경우 N은 치킨집의 개수
 출발지(집)에 대해 모든 조합 원소들의 거리 탐색(치킨집)
 집의 개수 2N개, 조합을 구하는 시간 N^2, 집 -> 조합 간의 거리 구하는 시간 2N * M^2
 전체 시간복잡도 : N^2 * 2N * M^2
 2500 * 100 * 169 = 42250000회
 시간이 왜 줄었는가?
 1. 조합의 경우 모든 nCr에서 모든 r에 대해인데, 그렇지는 않았다.
 -> r이 하나의 경우이기 때문에, 아무리 커도 절반 언저리
 그래서 절반을 까면 약 2천만회, 200ms정도
 

 3.

 */

package algo;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BJ_G5_15686 {
	static int N;
	static int M;
	static int[][] arr;
	static ArrayList<address> houseArr = new ArrayList<>();
	static int count;
	static ArrayList<address> bbqArr = new ArrayList<>();
	static int[] isVisited;
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][N];
		count = Integer.MAX_VALUE;
		
		// 수열구하고 치킨집개수구하고
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] == 1) houseArr.add(new address(j, i));
				else if(arr[i][j] == 2) bbqArr.add(new address(j, i));
			}
		}

		isVisited = new int[bbqArr.size()];

		// 순열을 구하는 방법. nPr
		// 순열이므로 중복된 조합이 발생할 수 있다.
		getCK(0, 0);
		
		bw.write(count + "\n");
		bw.flush();
		bw.close();
	}
	
	private static void getCK(int nth, int cnt) {
		if(cnt == M) {
			int tempLowestCount = 0;
			int tempLowSubCount = 0;
			int sum;

			for(int i = 0; i < houseArr.size(); i++) {
				// 순열이 구해지면 가게마다 거리 구하고 최소면 더하기
				tempLowSubCount = Integer.MAX_VALUE;
				for (int k = 0; k < isVisited.length; k++) {
					if(isVisited[k] == 1) {
						sum = Math.abs(houseArr.get(i).x - bbqArr.get(k).x)
								+ Math.abs(houseArr.get(i).y - bbqArr.get(k).y);
						tempLowSubCount = Math.min(sum, tempLowSubCount);
					}
				}
				tempLowestCount += tempLowSubCount;
			}
			count = Math.min(tempLowestCount, count);
			return;
		}
		
		for(int i = nth; i < bbqArr.size(); i++) {
			if(isVisited[i] == 0) {
				isVisited[i] = 1;
				getCK(i + 1, cnt + 1);
				isVisited[i] = 0;
			}
		}
	}

	public static class address{
		int x;
		int y;

		public address(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
