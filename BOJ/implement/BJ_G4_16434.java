package BOJ.implement;

import BOJ.Solve;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_G4_16434 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        long ATK = Integer.parseInt(str[1]);

        Room[] rooms = new Room[N];

        for(int i = 0; i < N; i++){
            str = br.readLine().split(" ");
            rooms[i] = new Room(
                    Integer.parseInt(str[0])
                    , Integer.parseInt(str[1])
                    , Integer.parseInt(str[2]));
        }

        long HP = 0;
        long needHP = 0;

        for(int i = 0; i < N; i++){
            Room now = rooms[i];
            if(now.t == 1){
                long knightAtkCount = 0;
                if(now.h % ATK == 0){
                    knightAtkCount = now.h / ATK;
                }
                else {
                    knightAtkCount = now.h / ATK + 1;
                }

                long monsterAtkCount = knightAtkCount - 1;

                HP -= monsterAtkCount * now.a;
                needHP = Math.min(needHP, HP);
            }
            else {
                HP += now.h;
                ATK += now.a;
                if(HP > 0) HP = 0;
            }
        }

        System.out.println(Math.abs(needHP) + 1);

    }

    static class Room{
        long t;
        long a;
        long h;
        Room(long t, long a, long h){
            this.t = t;
            this.a = a;
            this.h = h;
        }
    }
}
