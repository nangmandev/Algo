/**

 @author 한규준
 @since 2023-08-06
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_11279.java
 @youtube
 @performance 27652KB, 264ms
 @category 힙 구현
 @note

 힙을 구현하면 됩니다.

 1. 노드를 받는다.
 2. 노드번호는 이렇게
 - root : 1
 - curnode : curnode
 - leftnode : curnode * 2
 - rightnode : curnode * 2 + 1
 - 노드를 넣는경우, 마지막에 넣고 올라오면서 비교한다.
 3. rootnode를 빼내면?
 - 마지막 노드를 맨 위로 올린다.
 - 내려가면서 비교한다.

-----
 추가할때는 상향식
 삭제할때는 하향식



 취소. 최대힙에서 최댓값 : rootnode를 빼내면?
 - 바로 밑의 두 노드 비교 -> 더 큰 값을 올린다.
 - 해당 과정을 끝까지 진행

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BJ_S2_11279 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N * 4];
        int temp = 0;
        int heapSize = 0;

        for(int i = 0; i < N; i++){
            temp = Integer.parseInt(br.readLine());
            // 0이 아닌 수를 받는경우 힙에 추가
            if (temp != 0) {
                arr[++heapSize] = temp;
                hipify(arr, heapSize);
            }
            // 0을 받는 경우 힙에서 빼고 출력
            else {
                if(heapSize == 0) bw.write(0 + "\n");
                else {
                    unhipify(arr, bw, heapSize);
                    heapSize--;
                }
            }
        }

        bw.flush();
        bw.close();
    }

    // 힙에 원소를 추가하는 함수
    private static void hipify(int[] arr, int nowNode){
        // 맨 마지막 노드에 추가하고 올라오면서 비교
        for(int i = nowNode; i > 1; i /= 2){
            if(arr[i / 2] < arr[i]){
                int temp = arr[i];
                arr[i] = arr[i / 2];
                arr[i / 2] = temp;
            } else break;
        }
    }

    // 힙에 원소를 제거하는 함수
    private static void unhipify(int[] arr, BufferedWriter bw, int heapSize) throws Exception{
        // 일단 루트노드를 출력한다.
        bw.write(arr[1] + "\n");

        // 마지막 값을 루트노드로 올린다.
        arr[1] = arr[heapSize];
        arr[heapSize--] = 0;

        for(int i = 1; i * 2 <= heapSize;){
            if(arr[i] > arr[i * 2] && arr[i] > arr[i * 2 + 1]){
                break;
            } else if(arr[i * 2] > arr[i * 2 + 1]){
                int temp = arr[i * 2];
                arr[i * 2] = arr[i];
                arr[i] = temp;
                i = i * 2;
            } else {
                int temp = arr[i * 2 + 1];
                arr[i * 2 + 1] = arr[i];
                arr[i] = temp;
                i = i * 2 + 1;
            }
        }
    }
}
