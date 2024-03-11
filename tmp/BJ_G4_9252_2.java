package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_G4_9252_2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str1 = br.readLine();
        String str2 = br.readLine();

        int[][] map = new int[str1.length() + 1][str2.length() + 1];

        for(int i = 0; i < str1.length(); i++){
            for(int j = 0; j < str2.length(); j++){
                if(str1.charAt(i) == str2.charAt(j)){
                    map[i + 1][j + 1] = Math.max(Math.max(map[i + 1][j], map[i][j + 1]), map[i][j] + 1);
                } else {
                    map[i + 1][j + 1] = Math.max(map[i + 1][j], map[i][j + 1]);
                }
            }
        }

        int res = map[str1.length()][str2.length()];
        int ptr = res - 1;
        int Y = str1.length();
        int X = str2.length();

        char[] resStr = new char[res];

        while(ptr != -1){
            int left = map[Y][X - 1];
            int up = map[Y - 1][X];
            int leftUp = map[Y - 1][X - 1];

            if(left == up && left == leftUp){
                if(left != map[Y][X]) {
                    resStr[ptr--] = str2.charAt(X - 1);
                }
                Y--;
                X--;
            }
            else if(left > up){
                X--;
            }
            else {
                Y--;
            }
        }

        for(char item : resStr){
            sb.append(item);
        }

        System.out.println(res + "\n" + sb);
    }
}
