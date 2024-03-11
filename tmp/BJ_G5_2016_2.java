package src.algo;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BJ_G5_2016_2 {
        // 처음 걸린 사람의 선호도
        static Like originIdx;
        static boolean changed;
        static Person[] p;

        public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();

            int T = Integer.parseInt(br.readLine());

            for(int tc = 0; tc < T; tc++) {
                p = new Person[11];
                p[1] = new Person();
                String[] str;

                for (int i = 2; i <= 10; i++) {
                    str = br.readLine().split(" ");
                    p[i] = new Person();
                    for (int j = 0; j < str.length; j++) {
                        p[i].like[j] = (Integer.parseInt(str[j]));
                    }
                }

                changed = false;
                originIdx = null;

                permu(0, new int[5], new boolean[11]);

                if (changed) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }

            System.out.print(sb);
        }

        public static class Person{
            Like wps;
            int[] like;
            int likeManIndex;
            Person(){
                like = new int[5];
                likeManIndex = 0;
            }
        }

        public static class Like{
            int priority;
            int idx;
            Like(int priority, int idx){
                this.priority = priority;
                this.idx = idx;
            }
        }

        public static void permu(int nth, int[] like, boolean[] visited){
            if(changed) return;
            if(nth == like.length){
                p[1].like = like;
                find();
                return;
            }

            for(int i = 0; i < like.length; i++){
                if(!visited[i]){
                    visited[i] = true;
                    like[nth] = i + 6;
                    permu(nth + 1, like, visited);
                    visited[i] = false;
                }
            }
        }

        public static void find(){

            Deque<Integer> deq = new ArrayDeque<>();
            for(int i = 6; i <= 10; i++){
                deq.add(i);
            }

            while(!deq.isEmpty()){
                int now = deq.poll();

                int priority = 0;
                int likeManIndex = p[now].likeManIndex;
                int manIndex = p[now].like[likeManIndex];

                for(int i = 0; i < 5; i++){
                    if(now == p[manIndex].like[i]){
                        priority = i;
                        break;
                    }
                }

                if(p[manIndex].wps == null){
                    p[manIndex].wps = new Like(priority, now);
                } else if(p[manIndex].wps.priority > priority){
                    p[p[manIndex].wps.idx].likeManIndex++;
                    deq.add(p[manIndex].wps.idx);
                    p[manIndex].wps = new Like(priority, now);
                } else {
                    p[now].likeManIndex++;
                    deq.add(now);
                }
            }

            // 확인
            if(originIdx == null){
                originIdx = p[1].wps;
            }
            else if(originIdx.priority > p[1].wps.priority){
                changed = true;
                return;
            }

            for(int i = 1; i <= 10; i++){
                p[i].wps = null;
                p[i].likeManIndex = 0;
            }

        }

}
