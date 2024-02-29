package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solve {
    static int N, M, K, height, start;
    static long[] arr, comp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        K = Integer.parseInt(str[2]);

        height = (int) Math.ceil(Math.log(N) / Math.log(2));
        start = (int) Math.pow(2, height);

        arr = new long[start * 2];
        comp = new long[start * 2];

        for(int i = start; i < start + N; i++){
            arr[i] = Long.parseLong(br.readLine());
        }

        for(int i = start * 2 - 1; i > 0; i--){
            arr[i / 2] += arr[i];
        }

        int a, b, c;
        long d;
        for(int i = 0; i < M + K; i++){
            str = br.readLine().split(" ");
            a = Integer.parseInt(str[0]);
            b = Integer.parseInt(str[1]);
            c = Integer.parseInt(str[2]);
            if(a == 1){
                d = Long.parseLong(str[3]);
                update(b, c, d);
            }
            else {
                sb.append(sum(b, c) + "\n");
            }
        }

        System.out.print(sb);
    }

    public static void update(int s, int e, long val){
        s += start - 1;
        e += start - 1;
        long cp = 1;
        while(s <= e){
            if(s % 2 == 1) updateComp(s++, cp, val);
            if(e % 2 == 0) updateComp(e--, cp, val);
            s /= 2;
            e /= 2;
            cp *= 2;
        }
    }

    public static long sum(int s, int e){
        s += start - 1;
        e += start - 1;
        long sum = 0;
        long cp = 1;
        while(s <= e){
            if(s % 2 == 1) sum += getSum(s++, cp);
            if(e % 2 == 0) sum += getSum(e--, cp);
            s /= 2;
            e /= 2;
            cp *= 2;
        }
        return sum;
    }

    public static void updateComp(int idx, long cp, long val){
        comp[idx] += val;
        idx /= 2;
        while(idx > 0){
            arr[idx] += val * cp;
            idx /= 2;
        }
    }

    public static long getSum(int idx, long cp){
        long sum = arr[idx];
        while(idx > 0){
            sum += comp[idx] * cp;
            idx /= 2;
        }
        return sum;
    }
}
