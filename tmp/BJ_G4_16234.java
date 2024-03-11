package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

//N x N크기의 땅.
//r행 c열이면 A[r][c]명의 인구가 살고있다.
//인접한 나라 사이에 국경선이 존재한다.
//모든 국경선은 정사각형 형태이다.
//인구 이동은 더이상 발생하지 않을때까지 지속된다.
//
//        1. 국경선을 공유하는 두 나라의 인구차이가 L이상 R이하면 해당 국경선 개방
//2. 위의 조건에 의해 열어야 하는 국경선이 모두 열리면 인구이동 시작
//3. 국경선이 열려있어 인접한 칸만을 이용해 이동 가능하면 연합
//4. 연합을 이루는 각 칸의 인구수는 (연합 인구수) / (연합국가 개수)가 된다. 소수점은 버린다.
//        5. 연합 해체, 국경선을 닫는다.
//
//각 국가의 인구수가 주어지면, 인구이동이 며칠 발생하는지 구하시오
//
//N L R
//각국가의 인구수.
//일수는 2000번보다 작거나 같은 입력만 주어진다.

public class BJ_G4_16234 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");

        int N = Integer.parseInt(tmp[0]);
        int L = Integer.parseInt(tmp[1]);
        int R = Integer.parseInt(tmp[2]);

        Country[][] pop = new Country[N][N];

        int tmpCnt = 1;
        Union[] unions = new Union[N * N + 1];
        for(int i = 0; i < N; i++){
            tmp = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                pop[i][j] = new Country(tmpCnt, tmpCnt, Integer.parseInt(tmp[j]));
                unions[tmpCnt] = new Union();
                unions[tmpCnt].pops = pop[i][j].pop;
                unions[tmpCnt++].nations = 1;
            }
        }

        // 어떻게 국경이 열린것을 표시하는가
        // 연합배열을 만든다. 2차원배열에는 인구수와 노드번호, 부모노드번호 표시(부모노드 초기값은 자기자신)
        // 연합배열의 노드는 총인구수, 연합국가수를 가진다.
        // 2차원배열 주변을 탐색하면서 연합이 가능하면 해당 노드의 부모노드에 자신의 부모노드를 표시한 뒤 부모노드 번호의 연합배열에 인구수 + 자기노드, 국가수 + 1
        // 전부 탐색한 이후 배열을 한바퀴 다시 돌면서 연합배열의 부모노드의 인구수 / 국가수로 자기자신을 채우고 부모노드를 자기자신으로 초기화
        // 마지막에 연합배열 초기화
        // 연합이 가능하면 hasChanged = true.
        // 2차원배열 탐색 한바퀴 돌았을때 hasChanged = false면 탈출, 아니면 hasChanged를 false로 초기화
        // 한바퀴 돌때마다 카운트를 센다.

        boolean hasChanged = true;

        int count = 0;

        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        Deque<int[]> deque = new ArrayDeque<>();

        while(hasChanged){
            hasChanged = false;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    deque.add(new int[]{i, j});

                    while(!deque.isEmpty()) {
                        int[] tmpYX = deque.poll();

                        for (int k = 0; k < 4; k++) {
                            int nY = tmpYX[0] + dy[k];
                            int nX = tmpYX[1] + dx[k];
                            if (nY < N && nY >= 0
                             && nX < N && nX >= 0
                             && pop[tmpYX[0]][tmpYX[1]].parent != pop[nY][nX].parent) {
                                if (Math.abs(pop[tmpYX[0]][tmpYX[1]].pop - pop[nY][nX].pop) >= L
                                 && Math.abs(pop[tmpYX[0]][tmpYX[1]].pop - pop[nY][nX].pop) <= R) {
                                    pop[nY][nX].parent = pop[tmpYX[0]][tmpYX[1]].parent;
                                    unions[pop[tmpYX[0]][tmpYX[1]].parent].pops += pop[nY][nX].pop;
                                    unions[pop[tmpYX[0]][tmpYX[1]].parent].nations++;
                                    deque.add(new int[]{nY, nX});
                                    hasChanged = true;
                                }
                             }
                        }
                    }
                }
            }

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    pop[i][j].pop = unions[pop[i][j].parent].pops / unions[pop[i][j].parent].nations;
                    pop[i][j].parent = pop[i][j].idx;
                }
            }

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    unions[pop[i][j].idx].nations = 1;
                    unions[pop[i][j].idx].pops = pop[i][j].pop;
                }
            }

            if(hasChanged) count++;
        }

        System.out.println(count);

    }

    static class Country{
        int parent;
        int idx;
        int pop;
        Country(int parent, int idx, int pop){
            this.parent = parent;
            this.idx = idx;
            this.pop = pop;
        }
    }

    static class Union{
        int pops;
        int nations;
    }

}
