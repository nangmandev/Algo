/**

 @author 한규준
 @since 2023-08-30
 @see https://www.acmicpc.net/problem/11723
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S5_11723.java
 @youtube
 @performance 299760KB, 1248ms
 @category 비트마스킹
 @note

 비트연산자 복습

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_S5_11723 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int M = Integer.parseInt(br.readLine());

        int r = 0;

        for(int i = 0; i < M; i++){
            String[] temp = br.readLine().split(" ");
            if(temp[0].equals("all")){
                r = (1 << 21) - 1;
                continue;
            }
            else if(temp[0].equals("empty")){
                r = 0;
                continue;
            }
            int num = Integer.parseInt(temp[1]);
            if(temp[0].equals("add")){
                r |= (1 << num);
            }
            else if(temp[0].equals("remove")){
                r &= ~(1 << num);
            }
            else if(temp[0].equals("check")){
                if((r & (1 << num)) == 0){
                    bw.write(0 + "\n");
                }
                else bw.write(1 + "\n");
            }
            else if(temp[0].equals("toggle")){
                r ^= (1 << num);
            }
        }

        bw.flush();
        bw.close();
    }
}
