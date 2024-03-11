/**

 @author 한규준
 @since 2023-08-11
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_15686_2.java
 @youtube
 @performance 13924KB, 172ms
 @category 조합
 @note

 플로이드 워셜로 거리 전부 구하고
 행 합 중 최대값 구하기
 0으로 초기화하지 말기
 INF쓰기


 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_G5_15686_2 {
	static int[][] arr;
	static ArrayList<House> houses = new ArrayList<>();
	static ArrayList<BBQ> bbqs = new ArrayList<>();
	static int minValue = Integer.MAX_VALUE;
	
	public static class House {
		int y;
		int x;
		public House(int y, int x) {
			this.y = y;
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		
	}
	
	public static class BBQ{
		int y;
		int x;
		public BBQ(int y, int x) {
			this.y = y;
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) {
					houses.add(new House(i, j));
				} else if(arr[i][j] == 2) {
					bbqs.add(new BBQ(i, j));
				}
			}
		}
		// 초기화 완료
		// 치킨집이 M개만큼 있어야 한다.
		// 전체 치킨집 중 M개의 조합을 구해야 한다.
		// 조합이므로, 최소시간이다.
		combi(0, new int[M][2], 0);
		
		System.out.println(minValue);
	}
	
	public static void combi(int nth, int[][] tempArr, int start) {
		if(nth == tempArr.length) {
			// 여기서 거리 계산
			int tempSum = 0;
			for(int i = 0; i < houses.size(); i++) {
				// 하나의 집에서 모든 치킨집 돌아보면서 최소거리탐색
				int oneHouse = Integer.MAX_VALUE;
				for(int j = 0; j < tempArr.length; j++) {
					if(Math.abs(houses.get(i).y - tempArr[j][0]) + Math.abs(houses.get(i).x - tempArr[j][1]) < oneHouse) {
						oneHouse = Math.abs(houses.get(i).y - tempArr[j][0]) + Math.abs(houses.get(i).x - tempArr[j][1]);
					}
				}
				// 하나의 집에서 최소거리 찾으면 더하기
				tempSum += oneHouse;
			}
			if(tempSum < minValue) {
				minValue = tempSum;
			}
			return;
		}
		
		for(int i = start; i < bbqs.size(); i++) {
			tempArr[nth][0] = bbqs.get(i).getY();
			tempArr[nth][1] = bbqs.get(i).getX();
			combi(nth + 1, tempArr, i + 1);
		}
	}
}
