/**

 @author 한규준
 @since 2023-08-07
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_1946.java
 @youtube
 @performance
 @category 정렬, 비교
 @note

앞 순위 순서대로 정렬하면
뒷순위만 비교하면 된다.
 

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeMap;

public class BJ_S1_1946 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		String[] temp;
		int A = 0;
		int B = 0;

		for(int i = 1; i <= T; i++){
			int N = Integer.parseInt(br.readLine());
			int count = 0;
			TreeMap<Integer, Integer> treeMap = new TreeMap<>();

			for(int j = 1; j <= N; j++){
				temp = br.readLine().split(" ");
				A = Integer.parseInt(temp[0]);
				B = Integer.parseInt(temp[1]);
				treeMap.put(A, B);
			}

			int min = treeMap.get(1);
			for(Integer k : treeMap.keySet()){
				if(k == 1) {
					count++;
				}
				else if(treeMap.get(k) < min){
					min = treeMap.get(k);
					count++;
				}
			}
			treeMap.clear();
			System.out.println(count);
		}
	}
}
