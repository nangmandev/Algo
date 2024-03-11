package BOJ.twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BJ_G4_16472 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 고양이 말 번역기
        // 문자열을 주면 N개 종류 알파벳을 가진 연속된 문자열만 인식
        // 번역기가 인식할 수 있는 최대 문자열의 길이는 얼마인가

        // 1 < N <= 26 (알파벳의 종류)
        // 1 <= 문자열의 길이 <= 100000
        // 문자열에는 알파벳 소문자만이 포함된다.

        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        Map<Character, Integer> map = new HashMap<>();

        int front = 0;
        int rear = 0;

        int max = 0;

        while(front != str.length()){
            // 다음 문자가 map에 존재하면
            // 전방 포인터 진행
            // 다음 문자가 map에 없으면
            // map에 자리가 날때까지 후방포인터를 끌어온다
            // 한번 돌릴때마다 count 비교

            char now = str.charAt(front++);

            if(map.containsKey(now)){
                map.put(now, map.get(now) + 1);
            }
            else {
                while(map.size() >= N){
                    char past = str.charAt(rear++);
                    if(map.get(past) == 1){
                        map.remove(past);
                    } else {
                        map.put(past, map.get(past) - 1);
                    }
                }
                map.put(now, 1);
            }

            max = Math.max(max, front - rear);

        }

        System.out.println(max);
    }
}
