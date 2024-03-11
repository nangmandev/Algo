package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_SWT_4012 {
	public static int N;
	public static int[][] nergy;
	public static int[] material;
	public static int count;
	public static int tempSum1;
	public static int tempSum2;
	public static int minDiff;
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			nergy = new int[N][N];
			count = N / 2;
			material = new int[N];
			minDiff = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				material[i] = i;
			}
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					nergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			combi(0, new int[N / 2], 0);
			
			bw.write("#" + tc + " " + minDiff + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	// 처음 두 조합을 짜는 함수
	private static void combi(int nth, int[] tempArr, int start) {
		if(nth == tempArr.length) {
			tempSum1 = 0;
			tempSum2 = 0;
			
			int[] tempMat = new int[N];
			
			for(int i = 0; i < N / 2; i++) {
				tempMat[tempArr[i]] = 1;
			}
			
			int[] tempArr2 = new int[N / 2];
			for(int i = 0, j = 0; i < N; i++) {
				if(tempMat[i] == 0) tempArr2[j++] = material[i];
			}
			
			combi1(0, new int[2], 0, tempArr);
			combi2(0, new int[2], 0, tempArr2);
			
			int tempMinus = Math.abs(tempSum1 - tempSum2);
			if(tempMinus < minDiff) minDiff = tempMinus;
			
			return;
		}
		
		for(int i = start; i < material.length; i++) {
			tempArr[nth] = material[i];
			combi(nth + 1, tempArr, i + 1);
		}
	}
	
	private static void combi1(int nth, int[] tempArr, int start, int[] subMat) {
		if(nth == 2) {
			tempSum1 += nergy[tempArr[0]][tempArr[1]];
			tempSum1 += nergy[tempArr[1]][tempArr[0]];
			return;
		}
		
		for(int i = start; i < N / 2; i++) {
			tempArr[nth] = subMat[i];
			combi1(nth + 1, tempArr, i + 1, subMat);
		}
	}
	
	private static void combi2(int nth, int[] tempArr, int start, int[] subMat) {
		if(nth == 2) {
			tempSum2 += nergy[tempArr[0]][tempArr[1]];
			tempSum2 += nergy[tempArr[1]][tempArr[0]];
			return;
		}
		
		for(int i = start; i < N / 2; i++) {
			tempArr[nth] = subMat[i];
			combi2(nth + 1, tempArr, i + 1, subMat);
		}
	}
}
