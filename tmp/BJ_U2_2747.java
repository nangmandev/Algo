package algo.src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_U2_2747 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[] arr = new long[46];

        arr[0] = 0;
        arr[1] = 1;

        for(int i = 2; i <= 45; i++){
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        System.out.println(arr[n]);
    }
}
