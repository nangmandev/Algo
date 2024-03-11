/**

 @author 한규준
 @since 2023-08-06
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_11286.java
 @youtube
 @performance
 @category 힙 구현
 @note

힙 구현실패...
 뻘짓하지 말것

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ_S1_11286 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int heapSize = 0;
        int temp = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            int abs1 = Math.abs(o1), abs2 = Math.abs(o2);
            if(abs1 == abs2) return o1 > o2 ? 1 : -1;
            return abs1 - abs2;
        });

        for(int i = 0; i < N; i++) {
            temp = Integer.parseInt(br.readLine());
            if(temp == 0){
                if(queue.isEmpty()) sb.append("0").append("\n");
                else sb.append(queue.poll()).append("\n");
            } else {
                queue.add(temp);
            }
        }

        System.out.println(sb);
    }
}
