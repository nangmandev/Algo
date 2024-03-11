package BOJ.binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_G5_31498 {
    static long A, B, C, D, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 토카는 집과 A만큼 떨어져있다
        // 돌돌이는 토카와 같은 방향으로 A + C만큼 떨어져있다
        // 집, 토카, 돌돌이는 직선상이다
        // 토카는 B만큼 이동하는데, 체력이 좋지 않아 이동가능거리가 K씩 줄어든다.
        // 0이하가 되면 움직이지 못한다

        // 돌돌이는 D만큼 이동한다.
        // 둘의 이동은 동시에 발생한다.
        // 돌돌이와 토카가 같거나. 동시에 집에 도착하거나. 돌돌이가 앞지르면 잡힌다.
        // 다만, 이동 시작시 같은 위치에 있는 것은 예외
        // 토카가 먼저 도착하면 문을 잠글 수 있다

        String[] str = br.readLine().split(" ");
        A = Long.parseLong(str[0]);
        B = Long.parseLong(str[1]);
        str = br.readLine().split(" ");
        C = Long.parseLong(str[0]);
        D = Long.parseLong(str[1]);
        K = Long.parseLong(br.readLine());

        // 몇 번의 이동으로 집에 들어갈 수 있는지
        // 잡히면 -1

        long DolDol = (long) Math.ceil((A + C) * 1.0 / D);

        // 토카가 갈 수 있는 최대치
        // -> B / K번 * D 만큼. + B % K
        long tmp = K != 0 ? B / K : A / B;
        long rest = K != 0 ? B % K : 0;

        // 집에 도달하지 못하는경우
        if(getDistance(tmp) + rest < A && K != 0){
            System.out.println(-1);
        }
        // 몇번만에 집에 도달하는지 확인
        else {
            long min = 0, max = tmp + 1, mid = 0;
            while(min < max){
                mid = (min + max) / 2;
                long tmpD = getDistance(mid);

                if(tmpD > A){
                    max = mid;
                } else {
                    min = mid + 1;
                }
            }

            long tmpT = 0;
            if(getDistance(mid + 1) >= A){
                tmpT = mid + 1;
            }
            if(getDistance(mid) >= A){
                tmpT = mid;
            }
            if(getDistance(mid - 1) >= A){
                tmpT = mid - 1;
            }

            if(tmpT >= DolDol){
                System.out.println(-1);
            }
            else {
                System.out.println(tmpT);
            }
        }

    }

    public static long getDistance(long n) {
        return (long) Math.ceil(1.0 * n * B - n * (n - 1) * K / 2);
    }
}
