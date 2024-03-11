/**

 @author 한규준
 @since 2023-08-02
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_5014.java
 @youtube
 @performance 36032KB, 160ms
 @category BFS
 @note

 */

package algo;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
public class BJ_S1_5014 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] str = br.readLine().split(" ");
        int F = Integer.parseInt(str[0]);
        int S = Integer.parseInt(str[1]);
        int G = Integer.parseInt(str[2]);
        int U = Integer.parseInt(str[3]);
        int D = Integer.parseInt(str[4]);
        int[] arr = new int[F + 1];
        int[] Vb = new int[F + 1];

        Deque<Integer> deque = new ArrayDeque<>();
        int flag = 0;

        deque.add(S);

        while(deque.size() != 0){
            int x = deque.pollFirst();
            Vb[x] = 1;
            if(x == G) {
                System.out.println(arr[x]);
                flag = 1;
                break;
            }
            if(1 <= x - D && Vb[x - D] == 0){
                deque.add(x - D);
                Vb[x - D] = 1;
                arr[x - D] = arr[x] + 1;
            }
            if(F >= x + U && Vb[x + U] == 0){
                deque.add(x + U);
                Vb[x + U] = 1;
                arr[x + U] = arr[x] + 1;
            }
        }

        if(flag == 0) System.out.println("use the stairs");

        bw.flush();
        bw.close();
    }
}
