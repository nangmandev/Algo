package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_G5_20055 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1 2 3
        // 6 5 4
        // 이렇게 돌아가는데
        // 1번은 올리는 위치, 3번은 내리는 위치가 된다.
        // 올리는 위치의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
        // 가장 먼저 벨트에 올라간 로봇부터 회전방향으로 한 칸 이동하고 해당 칸의 내구도를 1내린다. 이동할 수 없으면 가만히 있는다.(이미 있는 로봇 X, 내구도 1이상)
        // 로봇이 내리는 위치에 도달하면 무조건 내린다.
        // 내구도가 0인 칸의 개수가 K개가 되면 과정을 종료한다.
        // 종료될 때 몇단계가 수행중인지 출력하시오

        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);

        Box[] arr = new Box[N * 2];
        str = br.readLine().split(" ");
        int zero = 0;
        for(int i = 0; i < N * 2; i++){
            arr[i] = new Box(i, Integer.parseInt(str[i]));
            if(arr[i].hp == 0) zero++;
        }

        int level = 0;

        while(zero < K){
            Box last = arr[N * 2 - 1];
            for(int i = N * 2 - 1; i > 0 ; i--){
                arr[i] = arr[i - 1];
            }
            arr[0] = last;
            level++;

            // 회전

            arr[N - 1].robot = false;
            for(int i = N - 1; i > 1; i--){
                if(arr[i - 1].robot && arr[i].hp > 0 && !arr[i].robot){
                    arr[i - 1].robot = false;
                    arr[i].robot = true;
                    arr[i].hp--;
                    if(arr[i].hp == 0) zero++;
                }
            }
            arr[N - 1].robot = false;
            // 로봇을 이동시킨다.

            if(arr[0].hp > 0){
                arr[0].hp--;
                if(arr[0].hp == 0) zero++;
                arr[0].robot = true;
            }
            // 로봇을 올린다.

            level++;
        }

        System.out.println(level / 2);
    }

    static class Box{
        int position;
        int hp;
        boolean robot;
        Box(int position, int hp){
            this.position = position;
            this.hp = hp;
            this.robot = false;
        }
    }
}
