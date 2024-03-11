/**

@author 한규준
@since 2023-08-01
@see
@git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D3_Flattern.java
@youtube
@performance 20272kb, 135ms
@category BruteForce, BackTracking, Permutation
@note

*/
package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_D3_Flattern {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= 10; tc++) {
			// 배열초기화
		    int[] arr = new int[100];
		    int dump = Integer.parseInt(br.readLine());
		    String[] str = br.readLine().split(" ");
		
		    for(int i = 0; i < arr.length; i++) {
			    arr[i] = Integer.parseInt(str[i]);
		    }
		
		    // dump만큼 돈다.
		    for(int i = 0; i < dump; i++) {
			    int max = Integer.MIN_VALUE;
			    int maxIdx = 0;
			    int min = Integer.MAX_VALUE;
			    int minIdx = 0;
			    //최대, 최소값 및 인덱스 갱신
			    for(int j = 0; j < arr.length; j++) {
				    if(max < arr[j]) {
					    max = arr[j];
					    maxIdx = j;
				    }
				    if(min > arr[j]) {
					    min = arr[j];
					    minIdx = j;
				    }
			    }
			    
			    // 최대, 최소값 수정
			    if(max != min) {
			        arr[maxIdx]--;
			        arr[minIdx]++;
			    }
			    
			    // 마지막에 최대, 최소값 갱신 이후 출력
			    if(i == dump - 1) {
			    	max = Integer.MIN_VALUE;
			    	min = Integer.MAX_VALUE;
			    	for(int k = 0; k < arr.length; k++) {
			    		if(max < arr[k]) max = arr[k];
			    		if(min > arr[k]) min = arr[k];
			    	}
				    System.out.printf("#%d %d\n", tc, max - min);
			    }
		    }
		}
	}
}
