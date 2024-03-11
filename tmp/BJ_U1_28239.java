/**

 @author 한규준
 @since 2023-09-02
 @see https://www.acmicpc.net/problem/28239
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_U1_28239.java
 @youtube
 @performance 19616KB, 188ms
 @category 비트마스킹
 @note

주어지는 수는 이진수이고
 1은 1~2개이므로 해당 수 검출하여 표시
 다만, 2, 4, 8과 같이 1이 하나밖에 없는 경우 유의

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_U1_28239 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            long m = Long.parseLong(br.readLine());
            String temp = Long.toBinaryString(m);

            int[] xy = new int[2];
            int idx = 0;
            for(int j = temp.length() - 1; j >= 0; j--){
                if(temp.charAt(j) == '1'){
                    xy[idx++] = (temp.length() - j - 1);
                }
            }
            if(idx == 1){
                xy[0] -= 1;
                xy[1] = xy[0];
            }

            bw.write(xy[0] + " " + xy[1] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
