/**

 @author 한규준
 @since 2023-09-16
 @see https://www.acmicpc.net/problem/2162
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_P5_2162.java
 @youtube
 @performance 15976KB, 472ms
 @category union-find, CCW
 @note

 CCW로 선분 교차 판정을 하고
 서로 교차하면 union-find 진행해서
 선분 그룹을 만들고
 2회 더 돌아서 같은 집합 개수를 세고 최대수 카운트

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_P5_2162 {
    static int N;
    static Line[] lines;

    static class Line {
        int x1;
        int y1;
        int x2;
        int y2;
        int parent;
        public Line(int x1, int y1, int x2, int y2, int parent){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.parent = parent;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        lines = new Line[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            lines[i] = new Line(x1, y1, x2, y2, i);
        }

        // 교차하는 경우 union find 진행
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(i == j) continue;
                if(isCross(i, j)){
                    union(i, j);
                }
            }
        }

        int[] result = new int[N];
        for(int i = 0; i < N; i++){
            result[lines[i].parent]++;
        }

        int maxValue = 0;
        int count = 0;
        for(int i = 0; i < N; i++){
            if(result[i] != 0) {
                maxValue = Math.max(maxValue, result[i]);
                count++;
            }
        }

        System.out.println(count + "\n" + maxValue);
    }

    private static int find(int a){
        if(lines[a].parent == a) return a;
        return lines[a].parent = find(lines[a].parent);
    }

    private static void union(int a, int b){
        int pA = find(a);
        int pB = find(b);
        if(pA != pB) {
            for (int i = 0; i < N; i++) {
                if (lines[i].parent == pB) {
                    lines[i].parent = pA;
                }
            }
        }
    }

    private static boolean isCross(int a, int b){
        // 교차 판단
        int x1 = lines[a].x1;
        int x2 = lines[a].x2;
        int y1 = lines[a].y1;
        int y2 = lines[a].y2;

        int x3 = lines[b].x1;
        int x4 = lines[b].x2;
        int y3 = lines[b].y1;
        int y4 = lines[b].y2;

        int ccw1 = CCW(x1, y1, x2, y2, x3, y3);
        int ccw2 = CCW(x1, y1, x2, y2, x4, y4);
        int ccw3 = CCW(x3, y3, x4, y4, x1, y1);
        int ccw4 = CCW(x3, y3, x4, y4, x2, y2);

        int rtn1 = ccw1 * ccw2;
        int rtn2 = ccw3 * ccw4;

        boolean cross = false;

        if(rtn1 == 0 && rtn2 == 0){
            // 두 선분이 일직선상에 있을 때
            // 먼저 x축 검사
            if(x1 != x2){
                if((Math.max(x1, x2) >= Math.min(x3, x4) && Math.min(x1, x2) <= Math.max(x3, x4))
                || (Math.max(x3, x4) >= Math.min(x1, x2) && Math.min(x3, x4) <= Math.max(x1, x2))){
                    cross = true;
                }
            }
            // y축
            else if(y1 != y2){
                if((Math.max(y1, y2) >= Math.min(y3, y4) && Math.min(y1, y2) <= Math.max(y3, y4))
                        || (Math.max(y3, y4) >= Math.min(y1, y2) && Math.min(y3, y4) <= Math.max(y1, y2))){
                    cross = true;
                }
            }
        } else if(rtn1 <= 0 && rtn2 <= 0){
            cross = true;
        }

        return cross;
    }

    private static int CCW(int x1, int y1, int x2, int y2, int x3, int y3){
        int rtn = x1*y2 + x2*y3 + x3*y1 - x2*y1 - x3*y2 - x1*y3;

        if(rtn > 0) return 1;
        else if(rtn < 0) return -1;
        else return 0;
    }
}