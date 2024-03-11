/**

 @author 한규준
 @since 2023-08-20
 @see https://www.acmicpc.net/problem/10775
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_10775.java
 @youtube
 @performance 21072KB, 208ms
 @category Union-Find
 @note

1. 배열을 그대로 탐색하면 시간초과 발생 우려가 있습니다.
 2. 한 비행기를 배열에 집어넣으면, 해당 배열은 이전의 사용 가능한 노드를 가리키게 합니다.
 3. 사용 가능한 배열(루트노드) : 인덱스와 값이 같은 노드'
 4. 사용 불가능한 배열(리프노드) : 인덱스와 값이 다른 노드
 5. 인덱스와 값이 다른 노드이면, 값을 인덱스로 계속 찾아간 뒤 해당 노드 값을 부모노드로 갱신합니다.
 6. 인덱스 0까지 추적해서 올라가면 더 이상 비행기를 도킹시킬 수 없습니다.
 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G2_10775 {
    static int G, P, gates[], count;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        count = 0;

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        gates = new int[G + 1];
        for(int i = 0; i < G + 1; i++){
            gates[i] = i;
        }

        // 비행기마다 최대노드로 이동
        // 비어있으면 find로 부모노드를 찾는다.
        // 노드에 넣으면, 이전 부모노드를 찾아 union
        for(int i = 0; i < P; i++){
            int airPlaneNum = Integer.parseInt(br.readLine());
            boolean tf = getTF(airPlaneNum);
            if(tf){
                count++;
            }
            else break;
        }

        System.out.println(count);
    }

    public static boolean getTF(int airPlaneNum){
        // 1. 가능한 게이트를 찾는다
        int gate = find(airPlaneNum);
        // 0이면 끝, 아니면 갱신
        if(gate == 0) return false;
        else {
            gates[airPlaneNum] = gate;
            gates[gate] = find(gate - 1);
            return true;
        }
    }

    public static int find(int num){
        // 부모노드이면 리턴, 아니면 찾으러 간다.
        if(gates[num] == num) return num;
        return find(gates[num]);
    }
}
