package src.algo;

import java.util.Scanner;

public class BJ_G5_2016 {
    static int[] myPreference = {6, 7, 8, 9, 10}; // 태현의 선호도
    static int[] outputPreference = new int[5];
    static int[] outputScores = new int[5];
    static int originPartner = 0;
    static int changePartner = 0;
    static int cnt = 0;

    // 짝 정하기
    public static void getPartner(int[][] preference, int[][] scores) {
        preference[1] = outputPreference;
        scores[1] = outputScores;
        int[] partners = new int[11]; // 파트너 정보 기록
        int[] findPartner = new int[5]; // 여학생의 파트너 정보 기록

        while (true) {
            boolean isContinue = false;
            for (int i = 6; i < 11; i++) {
                if (partners[i] != 0) continue; // 이미 짝을 지정한 여학생은 짝 선택을 하지 않는다.
                int idx = findPartner[i - 6]; // 여학생이 원하는 남학생 우선순위 (인덱스)
                findPartner[i - 6] = idx + 1; // 여학생이 다시 짝을 찾을 때 사용할 인덱스

                int wantPartner = preference[i][idx]; // 여학생이 원하는 남학생
                if (partners[wantPartner] == 0) { // 아직 짝이 정해지지 않음
                    partners[wantPartner] = i;
                    partners[i] = wantPartner;
                } else {
                    isContinue = true;
                    // 더 선호하는 여학생인지 비교
                    int decidedPartner = partners[wantPartner]; // 남학생과 짝인 여학생 번호
                    if (scores[wantPartner][decidedPartner - 6] > scores[wantPartner][i - 6]) { // 현재 여학생의 선호도가 더 높으면
                        partners[wantPartner] = i;
                        partners[i] = wantPartner;
                        partners[decidedPartner] = 0; // 기존에 짝인 여학생은 새로 지정해야 한다.
                    }
                }
            }
            if (!isContinue) break;
        }

        if (cnt == 0) {
            originPartner = partners[1];
        } else {
            changePartner = partners[1];
        }

    }

    // 태현의 선호도 생성
    public static void setPreference(int len, int[][] preference, int[][] scores, boolean[] used) {
        if (1 < cnt && originPartner > changePartner) {
            return;
        }

        if (len == 5) {
            // 선호도 결과 구하기
            getPartner(preference, scores);
            cnt++;
            return;
        }

        for (int idx = 0; idx < 5; idx++) {
            if (!used[idx]) {
                used[idx] = true;
                int partner = myPreference[idx];
                outputPreference[len] = partner;
                outputScores[partner - 6] = len;
                setPreference(len + 1, preference, scores, used);
                used[idx] = false;
            }
        }
    }

    public static String solution(int[][] preferences, int[][] scores) {
        boolean[] used = new boolean[5]; // 선호도 사용 기록
        cnt = 0;

        // 선호도 계산
        setPreference(0, preferences, scores, used);

        if (originPartner > changePartner) {
            return "YES";
        } else {
            return "NO";
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testcase = input.nextInt();
        int[][] preferences = new int[11][5];
        int[][] scores = new int[11][5]; // 우선순위에 따른 점수

        for (int tc = 0; tc < testcase; tc++) {
            // 선호도 입력
            for (int i = 2; i < 11; i++) {
                for (int j = 0; j < 5; j++) {
                    int partner = input.nextInt();
                    preferences[i][j] = partner;
                    if (partner > 5) partner -= 5;
                    scores[i][partner - 1] = j;
                }
            }
            System.out.println(solution(preferences, scores));
        }

    }
}