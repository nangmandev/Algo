/**

 @author 한규준
 @since 2023-08-21
 @see https://www.acmicpc.net/problem/6987
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_6987.java
 @youtube
 @performance 11656KB, 80ms
 @category 재귀, 경우 쪼개기
 @note

1. 한 라운드에 가능한 모든 경기 수 구하기
 2. 라운드마다 해당 경기만큼 진행하는데,
 3. 승/무/패 결과에 따라 DFS진행
 4. 이때 전부 브루트포스로 진행하면 오래걸림
 5. 따라서, 이 경우는 0부터 5까지 올라가면서 채우는 모양이므로
 6. 원본 배열보다 승/무/패가 커지면 더 이상 진행하지 않는 식으로 가지치기
 7. 또, 이미 정답이 발견된 경우 더 이상 탐색을 진행하지 않음으로 남은 가지도 쳐내기
 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_6987 {
	static int[][] arr;
	static int[][] test;
	static int flag;

	static int[][] combi = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5},
							{1, 2}, {1, 3}, {1, 4}, {1, 5},
							{2, 3}, {2, 4}, {2, 5},
							{3, 4}, {3, 5},
							{4, 5}};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(int tc = 0; tc < 4; tc++) {
			st = new StringTokenizer(br.readLine());
			arr = new int[6][3];
			test = new int[6][3];
			flag = 0;
		
			for(int i = 0; i < 6; i++) {
				int wt = Integer.parseInt(st.nextToken());
				int tt = Integer.parseInt(st.nextToken());
				int lt = Integer.parseInt(st.nextToken());
				
				arr[i][0] = wt;
				arr[i][1] = tt;
				arr[i][2] = lt;
			}

			DFS(0);

			if(flag == 1) bw.write(1 + " ");
			else bw.write(0 + " ");
		}
		
		bw.flush();
		bw.close();
	}
	
	public static void DFS(int round){
		if(flag == 1) return;
		boolean TF = true;
		if(round == 15){
			for(int i = 0; i < 6; i++){
				for(int j = 0; j < 3; j++){
					if(test[i][j] != arr[i][j]){
						TF = false;
						break;
					}
				}
			}
			if(TF) flag = 1;
			return;
		}

		int team1 = combi[round][0];
		int team2 = combi[round][1];

		if(test[team1][0] < arr[team1][0] && test[team2][2] < arr[team2][2]) {
			test[team1][0]++;
			test[team2][2]++;
			DFS(round + 1);
			test[team1][0]--;
			test[team2][2]--;
		}

		if(test[team1][1] < arr[team1][1] && test[team2][1] < arr[team2][1]) {
			test[team1][1]++;
			test[team2][1]++;
			DFS(round + 1);
			test[team1][1]--;
			test[team2][1]--;
		}

		if(test[team1][2] < arr[team1][2] && test[team2][0] < arr[team2][0]) {
			test[team1][2]++;
			test[team2][0]++;
			DFS(round + 1);
			test[team1][2]--;
			test[team2][0]--;
		}

	}
}
