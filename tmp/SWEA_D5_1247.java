package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_D5_1247 {
	static int[][] arr;
	static int N, minCount;
	static int[][] home;
	static int[][] work;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			minCount = Integer.MAX_VALUE;
			arr = new int[N][2];
			home = new int[1][2];
			work = new int[1][2];
			
			st = new StringTokenizer(br.readLine());
			home[0][0] = Integer.parseInt(st.nextToken());
			home[0][1] = Integer.parseInt(st.nextToken());
			work[0][0] = Integer.parseInt(st.nextToken());
			work[0][1] = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < N; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			permu(0, new int[N][2], new boolean[N]);
			
			bw.write("#" + tc + " " + minCount + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	static void permu(int nth, int[][] tempArr, boolean[] visited) {
		if(nth == N) {
			int tempSum = 0;
			tempSum += dis(work[0][0], work[0][1], tempArr[0][0], tempArr[0][1]);
			tempSum += dis(tempArr[N - 1][0], tempArr[N - 1][1], home[0][0], home[0][1]);
			
			for(int i = 0; i < N - 1; i++) {
				tempSum += dis(tempArr[i][0], tempArr[i][1], tempArr[i + 1][0], tempArr[i + 1][1]);
			}
			
			if(tempSum < minCount) minCount = tempSum;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				tempArr[nth][0] = arr[i][0];
				tempArr[nth][1] = arr[i][1];
				permu(nth + 1, tempArr, visited);
				visited[i] = false;
			}
		}
	}
	
	static int dis(int sX, int sY, int dX, int dY) {
		return Math.abs(sX - dX) + Math.abs(sY - dY);
	}
}
