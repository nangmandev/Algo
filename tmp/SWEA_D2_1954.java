/**

 @author 한규준
 @since 2023-08-02
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D2_1954.java
 @youtube
 @performance 18616KB, 100ms
 @category #구현, 문제읽기
 @note

 // 바깥쪽에서부터 안쪽으로 들어가면서 카운트를 센다.
 // 배열 바깥으로 나가려 하거나, 이미 입력된 칸을 만나면 방향전환

 */


package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_D2_1954 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 우측, 아래, 좌측, 위 이므로 델타x, 델타y를 사용
        int T = Integer.parseInt(br.readLine());
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for(int i = 1; i <= T; i++){
            int N = Integer.parseInt(br.readLine());
            // 배열 초기화 및 처음 값 지정
            int[][] arr = new int[N][N];
            arr[0][0] = 1;
            // 처음 값을 2로 지정했으므로 카운트는 2부터 시작
            int x = 0, y = 0, deltaIdx = 0, count = 2;

            while(count <= N * N){
                // 다음 값을 확인
                int nextX = dx[deltaIdx] + x;
                int nextY = dy[deltaIdx] + y;
                // 다음 값이 배열 내부이고 겹치지 않는다면 갱신
                if(nextX >= 0 && nextX < N && nextY>= 0 && nextY < N && arr[nextY][nextX] == 0) {
                    arr[nextY][nextX] = count++;
                    x = nextX;
                    y = nextY;
                } else {
                    // 그렇지 않는다면 방향전환, 갱신 안함
                    deltaIdx = (deltaIdx + 1) % 4;
                }
            }

            // 출력
            bw.write("#" + i + "\n");
            for(int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    bw.write(arr[j][k] + " ");
                }
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
