package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_G4_9252 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String st1 = br.readLine();
        String st2 = br.readLine();
        int N = st1.length();
        int M = st2.length();

        int[][]arr = new int[N + 1][M + 1];

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                if(st1.charAt(i - 1) == st2.charAt(j - 1)){
                    arr[i][j] = Math.max(Math.max(arr[i - 1][j], arr[i][j - 1]), arr[i - 1][j - 1] + 1);
                }
                else {
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
                }
            }
        }

        Stack<Character> stack = new Stack<>();
        int y = N;
        int x = M;
        while(y != 0 && x != 0){
            int u = arr[y - 1][x];
            int l = arr[y][x - 1];
            int lu = arr[y - 1][x - 1];

            if(u == l && u == lu){
                if(arr[y][x] != l) {
                    stack.push(st2.charAt(x - 1));
                }
                y--;
                x--;
            }
            else if(u > l){
                y--;
            }
            else{
                x--;
            }
        }

        System.out.println(arr[N][M]);
        while(!stack.isEmpty()){
            System.out.print(stack.pop());
        }
    }
}
