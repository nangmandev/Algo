/**

 @author 한규준
 @since 2023-08-03
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_2961.java
 @youtube
 @performance 11532KB, 80ms
 @category
 @note

 도영이는 짜파구리 요리사다.
 재료는 N개 있다.
 재료마다 신맛은 S
 재료마다 쓴맛은 B
 요리의 신맛은 신맛의 곱이고
 요리의 쓴맛은 쓴맛의 합이다.
 이때, 신맛과 쓴맛의 차이가 가장 작은 요리를 만드는 프로그램
 
 Math.abs(S곱 - B합)
 재료는 적어도 하나 이상 사용해야 한다.
 
 부분수열.
 순서 없이 짜도 의미가 없다
 
 조합은 반복문돌려서 nCr r범위 1 <= r <= n으로 해야하므로
 부분수열 한번만 돌려서 해결
 
 신맛과 쓴맛이 모두 양수이므로
 쓴맛이 0이 아닌경우만 체크하면 된다.
 -> 신맛은 1인경우 같기때문에

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_S2_2961 {
	static int N;
	static int[] sinFlavor;
	static int[] sseunFlavor;
	static int gap;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp;
		N = Integer.parseInt(br.readLine());
		sinFlavor = new int[N]; sseunFlavor = new int[N];
		gap = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			sinFlavor[i] = Integer.parseInt(temp[0]);
			sseunFlavor[i] = Integer.parseInt(temp[1]);
		}
		
		subSet(0, new int[N]);
		
		System.out.println(gap);
	}
	
	// 몇번쨰 수를 정하는지
	// 방문한 곳 확인
	
	public static void subSet(int nth, int[] resSubSet) {
		if(nth == resSubSet.length) {
			int sin = 1;
			int sseun = 0;
			for(int i = 0; i < resSubSet.length; i++) {
				if(resSubSet[i] == 1) {
					sin *= sinFlavor[i];
					sseun += sseunFlavor[i];
				}
			}
			if(sseun != 0) gap = Math.min(gap, Math.abs(sin - sseun));
			return;
		}
		
		resSubSet[nth] = 1;
		subSet(nth + 1, resSubSet);
		resSubSet[nth] = 0;
		subSet(nth + 1, resSubSet);
	}
}
