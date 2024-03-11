/**

 @author 한규준
 @since 2023-08-02
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S4_1620.java
 @youtube
 @performance 72296KB, 604ms
 @category #자료구조
 @note


 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class BJ_S4_1620 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]), M = Integer.parseInt(str[1]);
        int number = 1;

        Map<String, String> map = new HashMap<>();

        for(int i = 0; i < N; i++){
            String temp = br.readLine();
            map.put(Integer.toString(number), temp);
            map.put(temp, Integer.toString(number++));
        }

        for(int i = 0; i < M; i++){
            String temp = br.readLine();
            bw.write(map.get(temp) + "\n");
        }

        bw.flush();
        bw.close();
    }
}
