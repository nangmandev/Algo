/**

 @author 한규준
 @since 2023-08-11
 @see https://www.acmicpc.net/problem/10868
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_10868.java
 @youtube
 @performance 64476KB, 596ms
 @category 세그먼트 트리
 @note

1. 세그먼트 트리로 구간최소를 구하는 문제입니다.
 2. 들어오는 값은 모두 리프노드이기 때문에, 리프노드를 모두 저장할 수 있는 만큼의 배열을 선언합니다.
 3. 리프노드의 주소는 배열의 절반부터 시작하기 때문에, 입력값을 해당 순서대로 저장합니다.
 4. 노드 * 2, * 2 + 1을 하면 좌/우측 노드를 볼 수 있습니다.
 5. 이를 이용해 좌우측 노드의 값 중 최소값을 부모노드로 올립니다. 이때, 미리 초기화해두지 않았다면 0을 조심합니다.
 6. start, end값을 받고 탐색합니다.
 7. start값이 우측노드에 있다면, 해당 값을 min_value와 비교하여 저장할지를 결정한 뒤 우측 부모노드로 보냅니다.
 8. start값이 좌측노드에 있다면, 부모노드로 올립니다.
 9. end값이 우측노드에 있다면, 부모노드로 올립니다.
 10. end값이 좌측노드에 있다면, 해당 값을 min_value와 비교하여 저장할지 결정하고 좌측 부모노드로 보냅니다.
 11. 해당 과정을 start와 end가 겹치거나 start가 더 커질때까지 진행합니다.
 12. 이렇게 되면, start와 end가 겹치는 노드를 min_value와 비교하면 구간최소를 logN으로 구할 수 있습니다.
 13. 업데이트 기능은 따로 구현하지 않아도 됩니다.
 14. 출력 형식에 주의합니다. 입출력이 10만번이기 때문에, System.out.printt를 사용하면 시간초과입니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G1_10868 {
    static int N, M, size, height;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 리프노드들을 저장해야 하는데, 완전이진트리는 리프노드가 최대노드의 절반만큼.
        // 그러므로, 트리 크기가 16이면 리프노드 크기가 8이라 안된다.
        height = (int)Math.ceil(Math.log(N) / Math.log(2));
        size = (int)Math.pow(2, height + 1);
        arr = new int[size + 1];

        int start = size / 2;
        for(int i = start; i < start + N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 최소 세그먼트 트리 만들기
        for(int i = start - 1; i >= 1; i--){
            if(arr[i * 2] == 0 && arr[i * 2 + 1] == 0) arr[i] = 0;
            else if(arr[i * 2] == 0) arr[i] = arr[i * 2 + 1];
            else if(arr[i * 2 + 1] == 0) arr[i] = arr[i * 2];
            else arr[i] = Math.min(arr[i * 2], arr[i * 2 + 1]);
        }

        // 초기화 끝

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(parse(a, b) + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static int parse(int start, int end){
        start += size / 2 - 1;
        end += size / 2 - 1;
        int minValue = Integer.MAX_VALUE;
        while(start <= end){
            if(start % 2 == 1){
                if(minValue > arr[start]) minValue = arr[start];
                start = (start + 1) / 2;
            } else {
                start /= 2;
            }
            if(end % 2 == 1){
                end /= 2;
            } else {
                if(minValue > arr[end]) minValue = arr[end];
                end = (end - 1) / 2;
            }
        }
        return minValue;
    }
}
