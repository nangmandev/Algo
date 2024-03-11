package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G4_12851 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int minValue = Integer.MAX_VALUE;

        int[] arr = new int[110000];

        Deque<Integer> deque = new ArrayDeque<>();
        int count = 0;
        deque.add(N);
        arr[N] = 0;

        if(N != K) {
            while (!deque.isEmpty()) {
                int nowP = deque.poll();
                if(arr[nowP] > minValue) continue;
                if (nowP == K) {
                    if (minValue > arr[nowP]) {
                        minValue = arr[nowP];
                        count = 1;
                    } else if (minValue == arr[nowP]) {
                        count++;
                    } else {
                        arr[nowP] = minValue;
                    }
                    continue;
                }

                if (nowP + 1 <= 100000 && nowP + 1 >= 0
                        && (arr[nowP + 1] == 0 || arr[nowP + 1] == arr[nowP] + 1)) {
                    deque.add(nowP + 1);
                    arr[nowP + 1] = arr[nowP] + 1;
                }
                if (nowP - 1 >= 0 && nowP - 1 <= 100000
                        && (arr[nowP - 1] == 0 || arr[nowP - 1] == arr[nowP] + 1)) {
                    deque.add(nowP - 1);
                    arr[nowP - 1] = arr[nowP] + 1;
                }
                if (nowP * 2 <= 100000 && nowP * 2 >= 0
                        && (arr[nowP * 2] == 0 || arr[nowP * 2] == arr[nowP] + 1)) {
                    deque.add(nowP * 2);
                    arr[nowP * 2] = arr[nowP] + 1;
                }
            }

            System.out.println(arr[K] + "\n" + count);
        } else System.out.println(0 + "\n" + 1);
    }
}
