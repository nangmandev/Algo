/**

 @author 한규준
 @since 2023-08-05
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_10845.java
 @youtube
 @performance 18436KB, 164ms
 @category 큐
 @note

이미 있는 자료구조를 쓰고
 문자열만 처리하면 됩니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class BJ_S4_10845 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] temp;

        Deque<Integer> deque = new ArrayDeque<>();
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            temp = br.readLine().split(" ");

            if(temp[0].equals("push")){
                deque.add(Integer.parseInt(temp[1]));
            } else if(temp[0].equals("pop")){
                if(deque.size() != 0) bw.write(deque.pollFirst() + "\n");
                else bw.write(-1 + "\n");
            } else if(temp[0].equals("size")){
                bw.write(deque.size() + "\n");
            } else if(temp[0].equals("empty")){
                if(deque.isEmpty()) bw.write(1 + "\n");
                else bw.write(0 + "\n");
            } else if(temp[0].equals("front")){
                if(deque.size() != 0) {
                    int num = deque.pollFirst();
                    bw.write(num + "\n");
                    deque.addFirst(num);
                } else bw.write(-1 + "\n");
            } else if(temp[0].equals("back")){
                if(deque.size() != 0) {
                    int num = deque.pollLast();
                    bw.write(num + "\n");
                    deque.add(num);
                } else bw.write(-1 + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
