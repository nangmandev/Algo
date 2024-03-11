/**

 @author 한규준
 @since 2023-08-19
 @see https://www.acmicpc.net/problem/17386
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_17386.java
 @youtube
 @performance 11528KB, 76ms
 @category 선분 교차 판단
 @note

1. CCW알고리즘을 구현하면 됩니다.
 2. 다만, CCW * CCW가 0이 나올 때 케이스 처리를 잘 해야 합니다.
 3. X나 Y축 하나를 고정해놓고 생각합니다.
 4. 그리고 X나 Y가 같을 때를 처리합니다.
 5. 마지막으로, CCW리턴값을 그대로 곱하면 오버플로우가 발생합니다.
 -> (1000000 * 1000000) * (1000000 * 1000000) long도 안됩니다.
 6. 따라서, CCW리턴값을 양수, 음수, 0으로만 구분할 수 있도록 처리합니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G3_17386 {
	public static class Line{
		long y1;
		long x1;
		long y2;
		long x2;
		public Line(long x1, long y1, long x2, long y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int flag = 0;
		Line[] lineList = new Line[2];
		
		for(int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			lineList[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		int ccw1 = CCW(lineList[0], lineList[1].y1, lineList[1].x1);
		int ccw2 = CCW(lineList[0], lineList[1].y2, lineList[1].x2);

		int ccw3 = CCW(lineList[1], lineList[0].y1, lineList[0].x1);
		int ccw4 = CCW(lineList[1], lineList[0].y2, lineList[0].x2);

		if(ccw1 * ccw2 == 0 && ccw3 * ccw4 == 0) {
			if(lineList[0].x1 != lineList[0].x2
			&& (Math.max(lineList[0].x1, lineList[0].x2) > Math.min(lineList[1].x1, lineList[1].x2) || Math.min(lineList[0].x1, lineList[0].x2) > Math.max(lineList[1].x1, lineList[1].x2))){
				flag = 1;
			}
			else if((Math.max(lineList[0].y1, lineList[0].y2) > Math.min(lineList[1].y1, lineList[1].y2)
					|| Math.min(lineList[0].y1, lineList[0].y2) < Math.max(lineList[1].y1, lineList[1].y2))){
				flag = 1;
			}
		}
		else if(ccw1 * ccw2 <= 0 && ccw3 * ccw4 <= 0) {
			flag = 1;
		}
		System.out.println(flag);
	}
	
	public static int CCW(Line d1, long dy, long dx) {
		long temp = (d1.x1 * d1.y2) + (d1.x2 * dy) + (dx * d1.y1) - (d1.x2 * d1.y1) - (dx * d1.y2) - (d1.x1 * dy);
		if(temp > 0) return 1;
		else if(temp < 0) return -1;
		else return 0;
	}
}
