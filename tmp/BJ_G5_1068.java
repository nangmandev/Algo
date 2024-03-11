package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BJ_G5_1068 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] str = br.readLine().split(" ");
        List<node> nodes = new ArrayList<>();
        for(int i = 0; i < n; i++){
            nodes.add(new node(n));
        }

        int start = 0;

        // i : node index
        // getnum : parent index
        for(int i = 0; i < n; i++){
            int parent = Integer.parseInt(str[i]);
            if(parent == -1) {
                nodes.get(i).parent = -1;
                start = i;
                continue;
            }
            nodes.get(i).parent = parent;
            nodes.get(parent).childCount++;
            nodes.get(parent).childs[i] = 1;
        }
        // 초기화 끝

        int remove = Integer.parseInt(br.readLine());
        int result = 0;

        if(nodes.get(remove).parent != -1){
            // 삭제하려는 노드가 최상위노드가 아니면
            nodes.get(nodes.get(remove).parent).childCount--;
            nodes.get(nodes.get(remove).parent).childs[remove] = 0;
            // 위에서 탐색하지 못하도록 수정하고 진행한다.

            Deque<Integer> deq = new ArrayDeque<>();
            deq.add(start);

            while(!deq.isEmpty()){
                int node = deq.poll();
                if(nodes.get(node).childCount != 0){
                    for(int j = 0; j < n; j++){
                        if(nodes.get(node).childs[j] == 1){
                            deq.add(j);
                        }
                    }
                } else {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    public static class node{
        int parent;
        int childCount;
        int[] childs;

        node(int size){
            childs = new int[size];
            childCount = 0;
        }
    }
}
