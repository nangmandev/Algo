/**

 @author 한규준
 @since 2023-08-28
 @see https://www.acmicpc.net/problem/17408
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_P3_17408.java
 @youtube
 @performance 82556KB, 916ms
 @category 세그먼트 트리
 @note

1. 문제에서 요구하는 사항은 특정 구간 내의 두 수의 합의 최대값임
 2. 따라서, 특정 구간에서 가장 큰 수, 두 번째로 큰 수를 구해야 함
 3. 가장 간단한 방법으로, 세그먼트 트리를 노드로 만들어 가장 큰 수/두 번째로 큰 수를 저장함
 4. 답을 구할 때, 노드를 이동하면서 가장 큰 값과 두 번째로 큰 값을 비교하면서 저장
 5. 이 두 값을 모두 구하고 더한 뒤 출력하면 끝

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_P3_17408 {
    static int N, M, height, start;
    static Node[] arr;
    static class Node{
        int first;
        int second;
        public Node(int first, int second){
            this.first = first;
            this.second = second;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        height = (int)Math.ceil(Math.log(N) / Math.log(2));
        start = (int)Math.pow(2, height);
        arr = new Node[start * 2];

        st = new StringTokenizer(br.readLine());
        for(int i = start; i < start + N; i++){
            arr[i] = new Node(Integer.parseInt(st.nextToken()), 0);
        }
        for(int i = start + N; i < start * 2; i++){
            arr[i] = new Node(0, 0);
        }
        for(int i = start - 1; i > 0; i--){
            arr[i] = new Node(0, 0);
            if(arr[i * 2].first >= arr[i * 2 + 1].first){
                arr[i].first = arr[i * 2].first;
                if(arr[i * 2].second >= arr[i * 2 + 1].first){
                    arr[i].second = arr[i * 2].second;
                }
                else arr[i].second = arr[i * 2 + 1].first;
            }
            else {
                arr[i].first = arr[i * 2 + 1].first;
                if(arr[i * 2].first > arr[i * 2 + 1].second){
                    arr[i].second = arr[i * 2].first;
                }
                else arr[i].second = arr[i * 2 + 1].second;
            }
        }

        M = Integer.parseInt(br.readLine());
        int a, b, c;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(a == 1) update(b, c);
            else {
                bw.write(getSum(b, c) + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static void update(int idx, int val){
        idx += start - 1;
        arr[idx].first = val;
        for(int i = idx / 2; i > 0; i /= 2){
            if(arr[i * 2].first >= arr[i * 2 + 1].first){
                arr[i].first = arr[i * 2].first;
                if(arr[i * 2].second >= arr[i * 2 + 1].first){
                    arr[i].second = arr[i * 2].second;
                }
                else arr[i].second = arr[i * 2 + 1].first;
            }
            else {
                arr[i].first = arr[i * 2 + 1].first;
                if(arr[i * 2].first > arr[i * 2 + 1].second){
                    arr[i].second = arr[i * 2].first;
                }
                else arr[i].second = arr[i * 2 + 1].second;
            }
        }
    }

    private static int getSum(int left, int right){
        left += start - 1;
        right += start - 1;
        int rtn = 0;
        int first = 0, second = 0;
        while(left <= right){
            if(left % 2 == 1){
                if(arr[left].first >= first) {
                    second = Math.max(first, arr[left].second);
                    first = arr[left].first;
                }
                else {
                    second = Math.max(second, arr[left].first);
                    second = Math.max(second, arr[left].second);
                }
                left++;
            }
            if(right % 2 == 0){
                if(arr[right].first >= first) {
                    second = Math.max(first, arr[right].second);
                    first = arr[right].first;
                }
                else {
                    second = Math.max(second, arr[right].first);
                    second = Math.max(second, arr[right].second);
                }
                right--;
            }
            left /= 2;
            right /= 2;
        }
        rtn = first + second;

        return rtn;
    }
}
