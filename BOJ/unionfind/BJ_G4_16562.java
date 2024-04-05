package BOJ.unionfind;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_G4_16562 {
    static int[] parents, money, need;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // N명의 학생
        // 모든 학생과 친구가 되고 싶다
        // 학생 i에게 Ai만큼의 돈을 주면 1달간 친구가 된다
        // k원의 돈이 있다.
        // 친구의 친구에게는 돈을 주지 않아도 된다
        // M은 친구관계 수

        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int K = Integer.parseInt(str[2]);

        money = new int[N + 1];
        parents = new int[N + 1];
        need = new int[N + 1];

        str = br.readLine().split(" ");
        for(int i = 1; i <= N; i++){
            money[i] = Integer.parseInt(str[i - 1]);
            parents[i] = i;
        }

        for(int i = 0; i < M; i++){
            str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            union(a, b);
        }

        int sum = 0;
        for(int i = 1; i <= N; i++){
            if(parents[i] == i){
                sum += money[i];
            }
        }

        if(sum <= K){
            System.out.println(sum);
        } else {
            System.out.println("Oh no");
        }

    }

    public static int find(int a){
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    public static void union(int a, int b){
        int pA = find(a);
        int pB = find(b);
        if(pA == pB) return;
        if(money[pA] < money[pB]){
            parents[pB] = pA;
        }
        else {
            parents[pA] = pB;
        }
    }
}
