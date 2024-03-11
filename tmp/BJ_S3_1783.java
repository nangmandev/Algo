/**

 @author 한규준
 @since 2023-08-02
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_1783.java
 @youtube
 @performance 11612KB, 84ms
 @category 경우의 수, 경계값 체크
 @note

 */

package algo;

import java.io.*;
public class BJ_S3_1783 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]), M = Integer.parseInt(str[1]);
        int count = 1;

        // 어쨌든 맨오른쪽가면 끝

        if(N == 1 || M == 1) ;
        else if(N == 2 && M <= 8) count += (M - 1) / 2;
        else if(N == 2 && M > 8) count += 3;
        else if(M < 5) count += M - 1;
        else if(M == 5) count += M - 2;
        else count += M - 3;

        bw.write(count + "\n");

        bw.flush();
        bw.close();
    }
}
