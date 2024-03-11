package algo;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BJ_G5_15686_4 {
    static int N;
    static int M;
    static int[][] arr;
    static ArrayList<address> houseArr = new ArrayList<>();
    static int count;
    static ArrayList<address> bbqArr = new ArrayList<>();
    static int[] np;

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N][N];
        count = Integer.MAX_VALUE;

        // 수열구하고 치킨집개수구하고
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
                if(arr[i][j] == 1) houseArr.add(new address(j, i));
                else if(arr[i][j] == 2) bbqArr.add(new address(j, i));
            }
        }

        // NP로 구하기
        np = new int[bbqArr.size()];
        // 1이 M개만큼 있어야 합니다
        for(int i = bbqArr.size() - 1; i >= bbqArr.size() - M; i--){
            np[i] = 1;
        }

        do{
            int tempLowestCount = 0;
            int tempLowSubCount = 0;
            int sum;

            System.out.println(Arrays.toString(np));

            for(int i = 0; i < houseArr.size(); i++) {
                // 조합마다 집 -> 치킨집과의 최소값 구하기
                tempLowSubCount = Integer.MAX_VALUE;
                for (int k = 0; k < bbqArr.size(); k++) {
                    if(np[k] == 1) {
                        sum = Math.abs(houseArr.get(i).x - bbqArr.get(k).x)
                                + Math.abs(houseArr.get(i).y - bbqArr.get(k).y);
                        tempLowSubCount = Math.min(sum, tempLowSubCount);
                    }
                }
                tempLowestCount += tempLowSubCount;
            }
            count = Math.min(tempLowestCount, count);
        }while(getCK());

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    private static boolean getCK() {
        // 1. 최고 정점 찾기
        int lastPeak = np.length - 1;
        while(lastPeak > 0 && np[lastPeak - 1] >= np[lastPeak]) {
            lastPeak--;
        }

        if(lastPeak == 0) {
            return false;
        }

        // 2. 새 지도자 찾아오기
        int nextBoss = np.length - 1;
        while(np[lastPeak - 1] >= np[nextBoss]) {
            nextBoss--;
        }

        // 3. 지도자 세대교체
        swap(np, nextBoss, lastPeak - 1);

        // 4. 새로운 조직의 시작 뒤쪽 정렬
        for(int left = lastPeak, right = np.length - 1; left < right; left++, right--) {
            swap(np, left, right);
        }

        return true;
    }

    static void swap(int[] p, int i, int j){
        int temp = p[i];
        p[i] = p[j];
        p[j] = temp;
    }

    public static class address{
        int x;
        int y;

        public address(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
