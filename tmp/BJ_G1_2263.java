/**

 @author 한규준
 @since 2023-08-18
 @see https://www.acmicpc.net/problem/2263
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_2263.java
 @youtube
 @performance 55720KB, 1756ms
 @category 트리, 순회
 @note

1. 후위순회는 부분트리의 정점을 끝에서부터 순서대로 가리킨다.
 2. 후위순회의 끝에서 거꾸로 탐색을 진행하고, 중위순회를 분할하여 좌/우로 나눈다.
 3. 우 -> 좌 -> 정점 순서대로 탐색을 진행한다.
 4. 해당 결과는 preorder가 거꾸로 된 결과다.
 5. 이 결과를 뒤집어 출력하면 끝

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G1_2263 {
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] inOrder;
    static int[] postOrder;
    static int idx;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        idx = N - 1;
        inOrder = new int[N];
        postOrder = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            inOrder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            postOrder[i] = Integer.parseInt(st.nextToken());
        }
        
        // 초기화 완료
        preOrder(0, N - 1);

        // 꺼낸 노드들의 순서를 거꾸로 뒤집어주면 완성
        String[] temp = sb.toString().split(" ");
        StringBuilder result = new StringBuilder();
        for(int i = temp.length - 1; i >= 0; i--){
            result.append(temp[i]).append(" ");
        }
        System.out.println(result);
    }

    static void preOrder(int start, int end){
        // 후위표기에서 하나씩 꺼내서 찾는다.
        int midIndex = 0;
        for(int i = end; i >= start; i--){
            if(inOrder[i] == postOrder[idx]){
                midIndex = i;
                break;
            }
        }
        // 해당하는 값은 부분트리의 루트노드이다.
        idx--;
        if(start == end){
            sb.append(inOrder[midIndex]).append(" ");
            return;
        }
        if(midIndex != end) preOrder(midIndex + 1, end);
        if(start != midIndex) preOrder(start, midIndex - 1);


        sb.append(inOrder[midIndex]).append(" ");
    }
}
