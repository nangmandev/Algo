/**

 @author 한규준
 @since 2023-08-09
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_16935.java
 @youtube
 @performance 59348KB, 388ms
 @category 구현, 문제읽기
 @note

문제 잘 읽고 구현
1번, 2번은 쉬움
3번, 4번은 좌표 잘 보고 구현
5번, 6번도 좌표 잘 보고 구현

여러 방법을 순차적으로 사용해야 할 수도 있음


 */
package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G5_16935 {
	private static int N;
	private static int M;
	private static int R;
	private static int[][] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		String[] Q = br.readLine().split(" ");
		int idx = 0;
		
		for(int i = 0; i < R; i++) {
			if(Q[idx].equals("1")) {
				reverseUpDown();
			} else if(Q[idx].equals("2")) {
				reverseLeftRight();
			} else if(Q[idx].equals("3")) {
				rotateRight();
			} else if(Q[idx].equals("4")) {
				rotateLeft();
			} else if(Q[idx].equals("5")) {
				windmill();
			} else if(Q[idx].equals("6")) {
				reverseWindmill();
			}
			idx++;
		}
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				bw.write(arr[i][j] + " ");
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		
	}
	
	private static void reverseUpDown() {
		int[][] tempArr = new int[N][M];
		for(int i = 0; i < N; i++) {
			tempArr[i] = arr[N - i - 1];
		}
		arr = tempArr;
	}
	
	private static void reverseLeftRight() {
		int[][] tempArr = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				tempArr[i][j] = arr[i][M - j - 1];
			}
		}
		arr = tempArr;
	}
	
	private static void rotateRight() {
		int[][] tempArr = new int[M][N];
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				tempArr[i][N - j - 1] = arr[j][i];
			}
		}
		arr = tempArr;
		N = arr.length;
		M = arr[0].length;
	}
	
	private static void rotateLeft() {
		int[][] tempArr = new int[M][N];
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				tempArr[M - i - 1][j] = arr[j][i];
			}
		}
		arr = tempArr;
		N = arr.length;
		M = arr[0].length;
	}
	
	private static void windmill() {
		int [][] tempArr = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(i < N / 2 && j < M / 2) {
					tempArr[i][j] = arr[i + N / 2][j];
				} else if(i < N / 2 && j >= M / 2) {
					tempArr[i][j] = arr[i][j - M / 2];
				} else if(i >= N / 2 && j >= M / 2) {
					tempArr[i][j] = arr[i - N / 2][j];
				} else if(i >= N / 2 && j < M / 2) {
					tempArr[i][j] = arr[i][j + M / 2];
				}
			}
		}
		arr = tempArr;
		N = arr.length;
		M = arr[0].length;
	}
	
	private static void reverseWindmill() {
		int[][] tempArr = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(i < N / 2 && j < M / 2) {
					tempArr[i][j] = arr[i][j + M / 2];
				} else if(i >= N / 2 && j < M / 2) {
					tempArr[i][j] = arr[i - N / 2][j];
				} else if(i >= N / 2 && j >= M / 2) {
					tempArr[i][j] = arr[i][j - M / 2];
				} else if(i < N / 2 && j >= M / 2) {
					tempArr[i][j] = arr[i + N / 2][j];
				}
			}
		}
		arr = tempArr;
		N = arr.length;
		M = arr[0].length;
	}
}
