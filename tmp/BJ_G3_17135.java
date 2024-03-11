package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G3_17135 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int N, M, D, arr[][];
	static int maxCount = 0;
	public static void main(String[] args) throws Exception{
		
		// N x M 배열, 각 칸에 있는 적 수 최대 하나
		// N번행의 바로 아래의 모든 칸에는 성이 있다.
		// 궁수 3명을 배치, 하나의 칸에는 최대 1명의 궁수
		// 턴마다 궁수는 적 하나를 공격, 모든 궁수는 동시에 공격
		// 거리가 D이하인 적 중 가장 가까운 적 공격
		// 적이 여럿이면 가장 왼쪽 우선, 같은 적이 여러 궁수에게 공격당하기 가능, 공격받은 적 게임에서 제외
		// 궁수의 공격이 끝나면 적이 아래로 한 칸 이동. 성이 있는 칸으로 오면 게임에서 제외
		// 궁수의 공격으로 제거할 수 있는 적의 최대 수 계산
		
		// 행 N, 열 M, 사거리 D, 적 1, 빈칸 0, 궁수 3명
		
		// N + 1으로 배열 만들고
		// nC3으로 조합 만들고
		// 조합마다 시뮬레이션
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		// 초기화 완료
		
		combi(0, 0, new int[M]);
		
		System.out.println(maxCount);
	}
	
	static void combi(int nth, int start, int tempArr[]) {
		if(nth == 3) {
			// 궁수 위치들의 조합 만들기
			int[][] newArr = new int[N + 1][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					newArr[i][j] = arr[i][j];
				}
			}
			newArr[N] = tempArr;
			game(newArr);
			return;
		}
		
		for(int i = start; i < M; i++) {
			tempArr[i] = 2;
			combi(nth + 1, i + 1, tempArr);
			tempArr[i] = 0;
		}
	}
	
	static void game(int[][] newArr) {
		// 우선순위 : 가장가까운 - 가장아래 - 가장왼쪽
		boolean isThereEnemy = true;
		int tempCount = 0;
		int p = 0;
		while(p < N) {
			isThereEnemy = false;
			int rIdx = 0;
			int[][] removeEnemys = new int[3][2];
			// 초기값이 0, 0이라서 지워질 수 있다.
			for(int i = 0; i < 3; i++) removeEnemys[i][0] = -1;
			// 적이 있으면 계속 진행
			for(int i = 0; i < M; i++) {
				// 궁수 찾으면
				if(newArr[N][i] == 2) {
					// 거리, y, x 좌표값을 저장한다.
					int distance = Integer.MAX_VALUE;
					int enemyY = 0;
					int enemyX = 0;
					// 거꾸로 올라가면서 1행씩 찾기
					for(int j = N - 1; j >= 0; j--) {
						for(int k = 0; k < M; k++) {
							// 적을 발견하면 거리 계산
							if(newArr[j][k] == 1) {
								int tempDistance = Math.abs(N - j) + Math.abs(i - k);
								// 일단은 거리가 최대사거리보다 가까워야 한다.
								if(tempDistance <= D) {
									if(tempDistance < distance) {
										distance = tempDistance;
										enemyY = j;
										enemyX = k;
									}
									else if(tempDistance == distance) {
										// 거리가 같으면 Y - X순으로 비교
//										if(enemyY < j) {
//											enemyY = j;
//											enemyX = k;
//										}
										// Y가 같으면 X비교
										if(enemyX > k) {
											enemyY = j;
											enemyX = k;
										}
									}
								}
							}
						}
					}
					// 최소거리 적을 저장하고 인덱스 + 1
					if(distance != Integer.MAX_VALUE) {
						removeEnemys[rIdx][0] = enemyY;
						removeEnemys[rIdx][1] = enemyX;
					}
					rIdx = rIdx + 1;
				}
			}
			// 모든 적을 찾음
			// 해당 적이 존재하면 좌표를 0으로 만들고 예비카운트 더하기
			for(int i = 0; i < 3; i++) {
				if(removeEnemys[i][0] != -1
				&& newArr[removeEnemys[i][0]][removeEnemys[i][1]] == 1) {
					newArr[removeEnemys[i][0]][removeEnemys[i][1]] = 0;
					tempCount = tempCount + 1;
				}
			}
			
			for(int i = N - 1; i >= 0; i--) {
				for(int j = 0; j < M; j++) {
					// 남은 적은 아래로 내려보낸다.
					// N - 1인경우는 그냥 없앤다.
					// 아래부터 탐색
					if(i == N - 1 && newArr[i][j] == 1) {
						newArr[i][j] = 0;
					}
					else if(newArr[i][j] == 1) {
						newArr[i][j] = 0;
						newArr[i + 1][j] = 1;
						isThereEnemy = true;
					}
				}
			}
			p++;
		}
		// 다 끝났을 떄
		maxCount = Math.max(tempCount, maxCount);
	}
}
