package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_S2_1654_2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        int K = Integer.parseInt(tmp[0]);
        int N = Integer.parseInt(tmp[1]);

        int[] lans = new int[K];
        long sum = 0, min = 0, max = 0, mid = 0;

        for(int i = 0; i < K; i++){
            lans[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, lans[i]);
        }

        while(min <= max){
            mid = (min + max) / 2;
            if(mid == 0) mid = 1;
            sum = 0;

            for(int i = 0; i < K; i++){
                sum += lans[i] / mid;
            }

            if(sum >= N){
                min = mid + 1;
                if(max == mid || max - 1 == mid) break;
            } else {
                max = mid - 1;
            }
        }

        long tmpMaxSum = 0;
        for(int i = 0; i < K; i++){
            tmpMaxSum += lans[i] / max;
        }

        long tmpMinSum = 0;
        for(int i = 0; i < K; i++){
            tmpMinSum += lans[i] / min;
        }

        if(tmpMaxSum >= N){
            System.out.println(max);
        }
        else if(sum >= N){
            System.out.println(mid);
        }
        else if(tmpMinSum >= N){
            System.out.println(min);
        }
    }
}
