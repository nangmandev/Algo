package BOJ.segmenttree;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_G1_2042 {
    static int N, M, K, height, start;
    static long[] arr;
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

        for(int i = start;  i < start + N; i++){
            arr[i] = Long.parseLong(br.readLine());
        }

        for(int i = start * 2 - 1; i > 0; i--){
            arr[i / 2] += arr[i];
        }

        int a, b;
        long c;
        for(int i = 0; i < M + K; i++){
            str = br.readLine().split(" ");
            a = Integer.parseInt(str[0]);
            b = Integer.parseInt(str[1]);
            c = Long.parseLong(str[2]);
            if(a == 1) update(b, c);
            else sb.append(sum(b, (int)c) + "\n");
        }

        System.out.print(sb);
    }

    public static void update(int idx, long num){
        idx += start - 1;
        arr[idx] = num;
        idx /= 2;
        while(idx > 0){
            arr[idx] = arr[idx * 2] + arr[idx * 2 + 1];
            idx /= 2;
        }
    }

    public static long sum(int s, int e){
        s += start - 1;
        e += start - 1;
        long sum = 0;
        while(s <= e){
            if(s % 2 == 1) sum += arr[s++];
            if(e % 2 == 0) sum += arr[e--];
            s /= 2;
            e /= 2;
        }
        return sum;
    }
}
