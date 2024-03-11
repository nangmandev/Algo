/**

 @author 한규준
 @since 2023-08-19
 @see https://www.acmicpc.net/problem/1700
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_1700.java
 @youtube
 @performance 11652KB, 84ms
 @category 그리디
 @note

1. 멀티탭이 가득 찼을 때 뭘 먼저 뽑아야 하는가
 -> 처음엔 사용횟수가 가장 적은 것이라 생각했는데 틀림
 -> 사용횟수가 아니라, 가장 늦게 사용하는 코드를 뽑아야 중복이 적어짐
 2. 현재 들어오는 순서별로 노드를 만들고 중복체크
 3. 코드를 꽂으면서 다음으로 같은 코드가 언제 들어오는지 체크
 4. 멀면 우선순위큐에서 뒤로 밀림. 더이상 사용되지 않으면 integer.max값

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G1_1700 {
    public static class Elec implements Comparable{
        int num;
        int weight;
        public Elec(int num, int weight){
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Object o) {
            Elec temp = (Elec) o;
            return temp.weight != this.weight ? Integer.compare(temp.weight, this.weight) : Integer.compare(temp.num, this.num);
        }

        @Override
        public boolean equals(Object obj){
            return this.num == ((Elec)obj).num;
        }

        @Override
        public int hashCode(){
            return Objects.hash(num);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = 0, K = 0, count = 0;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // 순서 배열
        int[] arr = new int[K];
        ArrayList<Elec> use = new ArrayList<>();
        PriorityQueue<Elec> queue = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < K; i++){
            int temp = Integer.parseInt(st.nextToken());
            arr[i] = temp;
            int flag = 0;
            for(int j = 0; j < use.size(); j++){
                if(use.get(j).num == temp){
                    flag = 1;
                    break;
                }
            }
            if(flag == 0){
                use.add(new Elec(temp, 1));
            }
        }

        for(int i = 0; i < K; i++){
            Elec temp = null;
            for(int j = 0; j < use.size(); j++){
                if(use.get(j).num == arr[i]){
                    temp = use.get(j);
                    break;
                }
            }

            int flag = 0;
            for(int j = i + 1; j < K; j++){
                if(arr[j] == temp.num){
                    flag = 1;
                    temp.weight = j;
                    break;
                }
            }
            if(flag == 0) temp.weight = Integer.MAX_VALUE;

            if(queue.size() < N && !queue.contains(temp)){
                queue.offer(temp);
            }
            else if(queue.contains(temp)){
                queue.remove(temp);
                queue.offer(temp);
            }
            else {
                Elec temp1 = queue.poll();
                queue.offer(temp);
                count++;
            }
        }

        System.out.println(count);
    }
}
