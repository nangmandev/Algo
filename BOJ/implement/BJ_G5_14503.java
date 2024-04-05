package BOJ.implement;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_G5_14503 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 청소되지 않으면 현재칸청소
        // 주변 4칸이 모두 청소된 칸이면
        // 바라보는 방향 유지한채로 한칸 후진. 벽이면 작동종료
        // 청소되지 않은 칸이 있으면 반시계방향으로 회전
        // 바라보는 방향 기준으로 앞쪽칸이 청소되지 않으면 한칸 전진

        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");
        Robot robot = new Robot(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));

        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            str = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(str[j]);
            }
        }

        int[] dY = {-1, 0, 1, 0};
        int[] dX = {0, 1, 0, -1};

        boolean dirty = false;
        int aroundY, aroundX, backY, backX, nextY, nextX;

        while(true){
            dirty = false;
            if(!visited[robot.y][robot.x]){
                robot.count++;
                visited[robot.y][robot.x] = true;
            }

            for(int i = 0; i < 4; i++){
                aroundY = robot.y + dY[i];
                aroundX = robot.x + dX[i];
                if(aroundY >= 0 && aroundY < N
                        && aroundX >= 0 && aroundX < M
                        && map[aroundY][aroundX] == 0
                        && !visited[aroundY][aroundX]){
                    dirty = true;
                }
            }

            if(dirty){
                robot.rotate();
                nextY = robot.y + dY[robot.direction];
                nextX = robot.x + dX[robot.direction];
                if(nextY >= 0 && nextY < N
                        && nextX >= 0 && nextX < M
                        && map[nextY][nextX] == 0
                        && !visited[nextY][nextX]){
                    robot.y = nextY;
                    robot.x = nextX;
                }
            } else {
                backY = robot.y + dY[(robot.direction + 2) % 4];
                backX = robot.x + dX[(robot.direction + 2) % 4];
                if(backY >= 0 && backY < N
                        && backX >= 0 && backX < M) {
                    if (map[backY][backX] == 1) {
                        break;
                    } else {
                        robot.y = backY;
                        robot.x = backX;
                    }
                }
            }
        }

        System.out.println(robot.count);

    }

    static class Robot{
        int y;
        int x;
        int direction;
        int  count;
        Robot(int y, int x, int d){
            this.y = y;
            this.x = x;
            this.direction = d;
        }

        void rotate(){
            this.direction -= 1;
            if(this.direction == -1){
                this.direction = 3;
            }
        }
    }
}
