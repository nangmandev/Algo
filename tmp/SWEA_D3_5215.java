package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_D3_5215 {
	private static int maxScore;
	private static Material[] mate;
	private static int N;
	private static int L;
	
	public static class Material{
		int score;
		int car;
		public Material(int score, int car) {
			this.score = score;
			this.car = car;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// 재료에 대한 점수
		// 재료에 대한 칼로리
		// 정해진 칼로리 이하의 조합에서 가장 높은 점수의 합
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int TC = 1; TC <= tc; TC++) {
			maxScore = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			mate = new Material[N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int tempScore = Integer.parseInt(st.nextToken());
				int tempCar = Integer.parseInt(st.nextToken());
				mate[i] = new Material(tempScore, tempCar);
			}
			
			subset(0, new Material[N]);
			
			bw.write("#" + TC + " " + maxScore + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	private static void subset(final int nth, Material[] tempArr) {
		if(nth == N) {
			int scoreSum = 0;
			int carSum = 0;
			for(int i = 0; i < N; i++) {
				if(tempArr[i] != null) {
				scoreSum += tempArr[i].score;
				carSum += tempArr[i].car;
				}
				if(carSum > L) return;
			}
			if(carSum <= L && scoreSum > maxScore) maxScore = scoreSum;
			return;
		}
		
		tempArr[nth] = mate[nth];
		subset(nth + 1, tempArr);
		tempArr[nth] = null;
		subset(nth + 1, tempArr);
	}
}
