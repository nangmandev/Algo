/**

 @author 한규준
 @since 2023-08-23
 @see https://www.acmicpc.net/problem/17281
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_17281.java
 @youtube
 @performance 25484KB, 816ms
 @category 구현, 문제읽기
 @note

⚾⚾⚾⚾⚾⚾⚾⚾⚾⚾
문제읽기

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G4_17281 {
	static int N;
	static int[][] scores;
	static int maxScore = 0;
	static int[] playerNum;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		playerNum = new int[]{2, 3, 4, 5, 6, 7, 8, 9};
		
		N = Integer.parseInt(br.readLine());
		scores = new int[N][9];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				scores[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 초기화 완료
		
		permu(0, new boolean[8], new int[8]);
		
		bw.write(maxScore + " ");
		bw.flush();
		bw.close();
	}
	
	private static void permu(int nth, boolean[] visited, int[] tempArr) {
		if(nth == tempArr.length) {
			int[] gameArr = new int[9];
			int idx = 0;
			for(int i = 0; i < 9; i++) {
				if(i == 3) continue;
				gameArr[i] = tempArr[idx++];
			}
			gameArr[3] = 1;
			
			game(gameArr);
			return;
		}
		
		for(int i = 0; i < 8; i++) {
			if(!visited[i]) {
				visited[i] = true;
				tempArr[nth] = playerNum[i];
				permu(nth + 1, visited, tempArr);
				visited[i] = false;
			}
		}
	}
	
	private static void game(int[] arr) {
		Deque<Integer> deq = new ArrayDeque<>();
		int outCount = 0;
		int in = 0;
		int score = 0;
		for(int i = 0; i < 9; i++) {
			deq.offer(arr[i]);
		}
		
		int[] ru = new int[3];
		int nowPlayer;
		
		while(true) {
			nowPlayer = deq.poll();
			
			if(scores[in][nowPlayer - 1] == 0) {
				outCount++;
				deq.offer(nowPlayer);
				if(outCount == 3) {
					outCount = 0;
					in++;
					if(in == N) break;
					for(int j = 0; j < 3; j++) {
						ru[j] = 0;
					}
					continue;
				}
			}
			else if(scores[in][nowPlayer - 1] == 1) {
				if(ru[2] != 0) score++;
				for(int j = 2; j >= 1; j--) {
					ru[j] = ru[j - 1];
				}
				ru[0] = nowPlayer;
				deq.offer(nowPlayer);
			}
			else if(scores[in][nowPlayer - 1] == 2) {
				if(ru[2] != 0) score++;
				if(ru[1] != 0) score++;
				ru[2] = ru[0];
				ru[1] = nowPlayer;
				ru[0] = 0;
				deq.offer(nowPlayer);
			}
			else if(scores[in][nowPlayer - 1] == 3) {
				if(ru[2] != 0) score++;
				if(ru[1] != 0) score++;
				if(ru[0] != 0) score++;
				for(int j = 0; j < 3; j++) ru[j] = 0;
				ru[2] = nowPlayer;
				deq.offer(nowPlayer);
			}
			else if(scores[in][nowPlayer - 1] == 4) {
				score++;
				if(ru[2] != 0) score++;
				if(ru[1] != 0) score++;
				if(ru[0] != 0) score++;
				for(int j = 0; j < 3; j++) ru[j] = 0;
				deq.offer(nowPlayer);
			}
		}
		
		maxScore = Math.max(score, maxScore);
	}
}
