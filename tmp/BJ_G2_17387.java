/**

 @author 한규준
 @since 2023-08-19
 @see https://www.acmicpc.net/problem/17387
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_17387.java
 @youtube
 @performance 11528KB, 84ms
 @category 선분 교차 판단
 @note

 1. CCW알고리즘을 구현하면 됩니다.
 2. 다만, CCW * CCW가 0이 나올 때 케이스 처리를 잘 해야 합니다.
 3. X나 Y축 하나를 고정해놓고 생각합니다.
 4. 그리고 X나 Y가 같을 때를 처리합니다.
 5. 마지막으로, CCW리턴값을 그대로 곱하면 오버플로우가 발생합니다.
 -> (1000000 * 1000000) * (1000000 * 1000000) long도 안됩니다.
 6. 따라서, CCW리턴값을 양수, 음수, 0으로만 구분할 수 있도록 처리합니다.

 ----위의 내용은 17386번과 같습니다.
 다만, 17387번 문제는 조금 더 채점이 까다롭습니다.
 (17386번은 대충CCW만 구현하면 통과)

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G2_17387 {
    public static class Dot{
        long x;
        long y;
        public Dot(long x, long y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        Dot dot1 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Dot dot2 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        Dot dot3 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Dot dot4 = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        int ccw1 = CCW(dot1.x, dot1.y, dot2.x, dot2.y, dot3.x, dot3.y);
        int ccw2 = CCW(dot1.x, dot1.y, dot2.x, dot2.y, dot4.x, dot4.y);
        int ccw3 = CCW(dot3.x, dot3.y, dot4.x, dot4.y, dot1.x, dot1.y);
        int ccw4 = CCW(dot3.x, dot3.y, dot4.x, dot4.y, dot2.x, dot2.y);

        int flag = 0;


        if(ccw1 * ccw2 == 0 && ccw3 * ccw4 == 0){
            if(dot1.x != dot2.x){
                if((Math.max(dot1.x, dot2.x) >= Math.min(dot3.x, dot4.x) && Math.min(dot1.x, dot2.x) <= Math.max(dot3.x, dot4.x))
                || (Math.min(dot1.x, dot2.x) <= Math.max(dot3.x, dot4.x) && Math.max(dot1.x, dot2.x) >= Math.min(dot3.x, dot4.x))){
                    flag = 1;
                }
            }
            else if((Math.max(dot1.y, dot2.y) >= Math.min(dot3.y, dot4.y) && Math.min(dot1.y, dot2.y) <= Math.max(dot3.y, dot4.y))
                    || (Math.min(dot1.y, dot2.y) <= Math.max(dot3.y, dot4.y) && Math.max(dot1.y, dot2.y) >= Math.min(dot3.y, dot4.y))){
                flag = 1;
            }
        }
        else if(ccw1 * ccw2 <= 0 && ccw3 * ccw4 <= 0){
            flag = 1;
        }

        System.out.println(flag);
    }

    public static int CCW(long x1, long y1, long x2, long y2, long x3, long y3){
        long temp = (x1 * y2) + (x2 * y3) + (x3 * y1) - (x2 * y1) - (x3 * y2) - (x1 * y3);
        if(temp > 0) return 1;
        else if(temp < 0) return -1;
        else return 0;
    }
}
