package algo.src.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_U1_1292 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[1001];

        int num = 1;
        int idx = 1;
        while(true){
            for(int i = num; i > 0; i--){
                if(idx != 1001){
                    arr[idx] = num + arr[idx - 1];
                    idx++;
                } else {
                    break;
                }
            }
            if(idx == 1001) break;
            num++;
        }

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        bw.write((arr[B] - arr[A - 1]) + "");

        bw.flush();
        bw.close();
    }
}
