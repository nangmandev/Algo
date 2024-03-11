package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_D3_1873 {
    static int H, W;
    static char[][] map;
    static Tank tank;
    static class Tank{
        int y;
        int x;
        char direction;
        public Tank(int y, int x, char direction){
            this.y = y;
            this.x = x;
            this.direction = direction;
        }
    }

    static int[] movY = {-1, 1, 0, 0};
    static int[] movX = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int TC = 1; TC <= tc; TC++){
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];

            for(int i = 0; i < H; i++){
                String temp = br.readLine();
                for(int j = 0; j < W; j++){
                    map[i][j] = temp.charAt(j);
                    if(map[i][j] == '>' || map[i][j] == '<' || map[i][j] == '^' || map[i][j] == 'v'){
                        tank = new Tank(i, j, map[i][j]);
                    }
                }
            }

            int N = Integer.parseInt(br.readLine());
            String order = br.readLine();

            for(int i = 0; i < N; i++){
                getOrder(order.charAt(i));
            }

            sb.append("#").append(TC).append(" ");
           for(int i = 0; i < H; i++){
               for(int j = 0; j < W; j++){
                   sb.append(map[i][j]);
               }
               sb.append("\n");
           }

           System.out.print(sb);
        }
    }

    static void getOrder(char oneOrder){
        if(oneOrder == 'U'){
            tank.direction = '^';
            map[tank.y][tank.x] = '^';
            if(tank.y - 1 >= 0 && map[tank.y - 1][tank.x] == '.'){
                map[tank.y][tank.x] = '.';
                map[tank.y - 1][tank.x] = '^';
                tank.y = tank.y - 1;
            }
        }
        else if(oneOrder == 'D'){
            tank.direction = 'v';
            map[tank.y][tank.x] = 'v';
            if(tank.y + 1 < H && map[tank.y + 1][tank.x] == '.'){
                map[tank.y][tank.x] = '.';
                map[tank.y + 1][tank.x] = 'v';
                tank.y = tank.y + 1;
            }
        }
        else if(oneOrder == 'R'){
            tank.direction = '>';
            map[tank.y][tank.x] = '>';
            if(tank.x + 1 < W && map[tank.y][tank.x + 1] == '.'){
                map[tank.y][tank.x] = '.';
                map[tank.y][tank.x + 1] = '>';
                tank.x = tank.x + 1;
            }
        }
        else if(oneOrder == 'L'){
            tank.direction = '<';
            map[tank.y][tank.x] = '<';
            if(tank.x - 1 >= 0 && map[tank.y][tank.x - 1] == '.'){
                map[tank.y][tank.x] = '.';
                map[tank.y][tank.x - 1] = '<';
                tank.x = tank.x - 1;
            }
        }
        else if(oneOrder == 'S'){
            int i = 0;
            if(tank.direction == '^') i = 0;
            else if(tank.direction == 'v') i = 1;
            else if(tank.direction == '<') i = 2;
            else if(tank.direction == '>') i = 3;

            int nextY = tank.y + movY[i];
            int nextX = tank.x + movX[i];
            while(nextY >= 0 && nextY < H
            && nextX >= 0 && nextX < W){
                if(map[nextY][nextX] == '.' || map[nextY][nextX] == '-'){
                    nextY = nextY + movY[i];
                    nextX = nextX + movX[i];
                }
                else if(map[nextY][nextX] == '*'){
                    map[nextY][nextX] = '.';
                    return;
                }
                else if(map[nextY][nextX] == '#') return;
            }
        }
    }
}
