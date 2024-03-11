/**

 @author 한규준
 @since 2023-08-24
 @see https://www.acmicpc.net/problem/11003
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_P5_11003.java
 @youtube
 @performance 715892KB, 2580ms
 @category 모노톤 큐
 @note

최소값을 항상 큐 앞에 가져다 놓는 것이 핵심
 1. 덱과 우선순위 큐를 같이 운용해, 덱에서 범위를 넘어 벗어난 것을 큐에서 제거하는 식 -> 시간초과
 2. 우선순위 큐와 값/인덱스를 같이 저장하는 number클래스를 사용해, 우선순위 큐에서 뽑은 숫자가 인덱스 초과면 그냥 버리는 식 -> 시간초과
 3. 덱에서 최소값과 인덱스를 같이 저장해 관리하는 식 -> 실패
 4. 모노톤 큐 발견 -> 적용

 1. 덱이 비어있으면 그냥 넣는다.
 2. 그렇지 않다면, 값을 받아와 덱의 뒤쪽부터 하나씩 자기보다 큰 값인지 비교한다.
 3. 자기보다 큰 값이면 뒤에서부터 빼고, 그렇지 않으면 그대로 쌓는다.
 4. 덱의 첫번째 값이 인덱스를 벗어나면 뺀다.
 5. 덱의 처음에 남은 값을 출력한다.
 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BJ_P5_11003 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        Deque<int[]> deq = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        int[] temp;
        for(int i = 0; i < N; i++){
            temp = new int[]{Integer.parseInt(st.nextToken()), i};
            if(deq.isEmpty()) {
                deq.offer(temp);
            }
            else {
                while(!deq.isEmpty()){
                    if(deq.peekLast()[0] > temp[0]){
                        deq.removeLast();
                    }
                    else break;
                }
                deq.offer(temp);
                if(deq.peekFirst()[1] < i - L + 1){
                    deq.removeFirst();
                }
            }
            bw.write(deq.peekFirst()[0] + " ");
        }
        bw.flush();
        bw.close();
    }
}
