/**

 @author 한규준
 @since 2023-08-21
 @see https://www.acmicpc.net/problem/2252
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_2252.java/
 @youtube
 @performance 47608KB, 460ms
 @category 위상정렬
 @note

A가 B보다 앞에 있으므로
 A -> B로 향하는 그래프로 생각할 수 있습니다.
 따라서, 해당 그래프의 위상정렬을 진행하면
 순서대로 세울 수 있습니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_G3_2252 {
    static int N, M;
    static int node[];
    static ArrayList<ArrayList<edge>> edges;

    static class edge{
        int start;
        int end;
        public edge(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        node = new int[N + 1];
        edges = new ArrayList<>();
        for(int i = 0; i <= N; i++){
            edges.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            node[b]++;
            edges.get(a).add(new edge(a, b));
        }

        // 초기화 완료
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 1; i <= N; i++){
            if(node[i] == 0) queue.offer(i);
        }
        // 차수가 0인 노드들을 큐에 넣는다.

        while(!queue.isEmpty()){
            int nowNode = queue.poll();
            bw.write(nowNode + " ");

            for(int i = 0; i < edges.get(nowNode).size(); i++){
                edge nowEdge = edges.get(nowNode).get(i);
                node[nowEdge.end]--;
                if(node[nowEdge.end] == 0){
                    queue.offer(nowEdge.end);
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
