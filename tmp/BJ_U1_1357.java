package algo.src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_U1_1357 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        sb1.append(st.nextToken()).reverse();
        String tmp1 = sb1.toString();
        sb2.append(st.nextToken()).reverse();
        String tmp2 = sb2.toString();

        int a = Integer.parseInt(tmp1);
        int b = Integer.parseInt(tmp2);

        sb3.append(a + b).reverse();

        System.out.println(Integer.parseInt(sb3.toString()));
    }
}
