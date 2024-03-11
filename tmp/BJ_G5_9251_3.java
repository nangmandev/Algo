package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_G5_9251_3 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int[][] map = new int[str1.length() + 1][str2.length() + 1];

        int max = 0;

        for(int i = 0; i < str1.length(); i++){
            for(int j = 0; j < str2.length(); j++){
                if(str1.charAt(i) == str2.charAt(j)){
                    map[i + 1][j + 1] = map[i][j] + 1;
                    max = Math.max(max, map[i + 1][j + 1]);
                }
                else {
                    map[i + 1][j + 1] = Math.max(map[i + 1][j], map[i][j + 1]);
                }
            }
        }

        System.out.println(max);
    }
}
