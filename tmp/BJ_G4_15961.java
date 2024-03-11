/**

 @author 한규준
 @since 2023-08-24
 @see https://www.acmicpc.net/problem/15961
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_15961.java
 @youtube
 @performance 356332KB, 812ms
 @category 투포인터, 자료구조
 @note

Deque2개를 이용해 내 접시, 벨트를 확인하면서 비교
다른 방법으로도 가능할듯?

1. 2개의 Deque를 사용합니다. 1개는 벨트, 1개는 내 접시입니다.
2. 회전초밥이 돌아가는 형태, 내가 선택하는 형태를 직접 그려보면, queue형태 혹은 덱 형태의 구조입니다.
3. 다만, 벨트에서 초밥을 가져올 때는 벨트의 뒤에서 초밥을 뺴 내 접시의 앞에 붙여야 합니다.
4. 내 접시에서 벨트에 다시 돌려놓는 경우에도, 접시의 뒤에서 초밥을 빼 벨트의 앞에 붙여야 합니다.
5. 따라서, 덱의 뒤와 앞을 잘 구별해야 합니다.
6. 카테고리 배열을 만들어, 어떠한 초밥을 몇 접시 가지고 있는지 확인합니다.
7. 초밥을 추가할 때, 해당 초밥 배열 상태가 1이 된다면 새 종류 추가이므로 종류 변수를 더합니다.
8. 초밥을 뺄 떄, 해당 초밥 배열 상태가 0이 된다면 가지고 있는 종류가 사라진 경우이므로 종류 변수를 뺍니다.
9. 종류를 확인할 때, 카테고리 배열의 쿠폰 인덱스가 0이라면 해당 종류의 초밥이 없는 것이므로 종류 + 1을 비교합니다.
10. 그렇지 않는다면 해당 종류의 초밥을 이미 가진 것이므로 그대로 비교합니다.
11. 답을 출력합니다.


 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G4_15961 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		// differCount변수는 지금 내가 얼마나 많은 종류를 가지는지 카운팅합니다.
		// catCount는 초밥 종류별로 얼마나 가지고 있는지 카운팅합니다.
		// maxCat은 경우의 수 별로 최대치를 체크합니다.
		int differCount = 0;
		int[] catCount = new int[d + 1];
		int maxCat = 0;

		// 두 개의 큐를 생성합니다.
		// 한 개는 벨트(회전초밥 벨트)
		// 한 개는 내 접시
		Deque<Integer> belt = new ArrayDeque<>();
		Deque<Integer> mine = new ArrayDeque<>();

		// 일단 첫 경우의 수를 체크하기 위해 내 접시에 먼저 k개만큼 연속적으로 추가합니다.
		// 종류별로 catCount에 갯수를 더합니다.
		// 이때, catCount값이 1이라면 원래 0이었던 것이기 때문에 새 종류가 추가된 것입니다.
		for(int i = 0; i < k; i++) {
			int cb = Integer.parseInt(br.readLine());
			mine.offer(cb);
			catCount[cb]++;
			if(catCount[cb] == 1) differCount++;
		}

		// 쿠폰에 적힌 초밥의 종류를 가지고 있지 않다면 하나 더 추가합니다.
		// 첫 경우의 수를 갱신합니다.
		if(catCount[c] == 0) maxCat = differCount + 1;
		else maxCat = differCount;
		// 첫 경우의 수

		// 나머지 초밥을 벨트에 추가합니다.
		for(int i = 0; i < N - k; i++) {
			belt.offer(Integer.parseInt(br.readLine()));
		}
		
		// 초기화 완료

		// 회전하는 초밥들과 내 구간을 그림을 그려 확인해보면
		// 내 구간의 앞에 초밥을 추가하려면 벨트의 뒷부분에서 초밥을 빼야 한다는 것을 알 수 있습니다.
		// 마찬가지로, 벨트에 초밥을 다시 되돌리려면 내 구간의 뒷부분에서 초밥을 뺴 벨트의 앞부분에 추가해야 한다는 것을 알 수 있습니다.
		// 따라서 일반적인 큐 대신 덱을 사용해 두 덱의 앞과 뒤에서 자유롭게 자료를 빼야 합니다.
		// 또한, 내 구간에서 초밥이 빠지면 바로바로 종류 체크를 하고
		// 내 구간에 초밥이 추가되면 바로바로 종류체크를 합니다.
		// 이렇게 되면, 마치 투 포인터와 같이 덱이 맞물려 들어갈 수 있습니다.
		for(int i = 0; i < N; i++) {
			int temp = mine.pollLast();
			catCount[temp]--;
			if(catCount[temp] == 0) differCount--;
			belt.offerFirst(temp);
			
			temp = belt.pollLast();
			catCount[temp]++;
			if(catCount[temp] == 1) differCount++;
			mine.offerFirst(temp);
			
			if(catCount[c] == 0) maxCat = Math.max(maxCat, differCount + 1);
			else maxCat = Math.max(maxCat, differCount);
		}

		// 결과를 출력합니다.
		System.out.println(maxCat);
	}
}
