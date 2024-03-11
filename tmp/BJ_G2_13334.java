package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G2_13334 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 집과 사무실을 통근하는 n명의 사람들
        // 각 사람의 집과 사무실을 수평선 상의 다른 위치이다
        // A, B에 대해서
        // A의 집/사무실의 위치가 B의 집/사무실의 위치와 같은 수 있따
        // 일직선 상의 두 점을 잇는 철로를 건설하여 기차를 운행하려 한다
        // 철로의 길이는 D이다
        // 집, 사무실의 위치가 모두 철로 선분에 포함되는 사람이 최대가 되도록 철로선분을 정하고자 한다
        // d, n개의 정수쌍(h, o)가 주어졌을때 길이 d의 모든 선분 L에 대해 집/사무실의 위치가 모두 L에 포함되는 사람들의 최대 수를 구하시오

        int N = Integer.parseInt(br.readLine());
        List<Line> homes = new ArrayList<>();
        String[] tmp;
        for (int i = 0; i < N; i++) {
            tmp = br.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            homes.add(new Line(Math.min(a, b), Math.max(a, b)));
        }

        Collections.sort(homes);

        long d = Integer.parseInt(br.readLine());
        // 초기화 완료

        int res = 0;
        int tmpCount = 0;
        // 초기 출발지점 설정

        PriorityQueue<Line> pq = new PriorityQueue<>(new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });

        for(Line line : homes){
            while(!pq.isEmpty() && pq.peek().start < line.end - d){
                tmpCount--;
                pq.poll();
            }

            // 선분을 골라넣는다.
            // 이때, start지점 순서대로 다시 정렬되어야 한다.
            if(line.start >= line.end - d){
                pq.offer(line);
                tmpCount++;
            }

            res = Math.max(res, tmpCount);
        }

        System.out.println(res);
    }

    static class Line implements Comparable<Line>{
        int start;
        int end;

        Line(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o) {
            return Integer.compare(this.end, o.end);
        }
    }
}
