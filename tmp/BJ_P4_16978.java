package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_P4_16978 {
	static int N, M, height, start;
	static long[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		height = (int)Math.ceil(Math.log(N) / Math.log(2));
		start = (int)Math.pow(2, height);
		arr = new long[start * 2];
		
		st = new StringTokenizer(br.readLine());
		for(int i = start; i < start + N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = start - 1; i > 0; i--) {
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
		}
		
		M = Integer.parseInt(br.readLine());
		int[][] oneQuery = new int[M + 1][2];
		long[][] twoQuery = new long[M][5];
		int oneIdx = 1;
		int twoIdx = 0;
		
		int a, b, c, d;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			if(a == 1) {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				oneQuery[oneIdx][0] = b;
				oneQuery[oneIdx++][1] = c;
				continue;
			}
			else {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				twoQuery[twoIdx][0] = b;
				twoQuery[twoIdx][1] = c;
				twoQuery[twoIdx][2] = d;
				twoQuery[twoIdx][4] = twoIdx++;
			}
		}
		// 쿼리들 저장 완료
		Arrays.sort(twoQuery, 0, twoIdx, (o1, o2) -> Long.compare(o1[0], o2[0]));
		// 순서대로 정렬
		
		int qIdx = 0;
		
		for(int i = 0; i < twoIdx; i++) {
			if(twoQuery[i][0] != qIdx) {
				while(qIdx != (int)twoQuery[i][0]) {
					qIdx++;
					update(oneQuery[qIdx][0], oneQuery[qIdx][1]);
				}
			}
			twoQuery[i][3] = getSum(twoQuery[i][1], twoQuery[i][2]);
		}
		
		Arrays.sort(twoQuery, 0, twoIdx, (o1, o2) -> Long.compare(o1[4], o2[4]));
		
		for(int i = 0; i < twoIdx; i++) {
			bw.write(twoQuery[i][3] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	private static void update(int idx, int val) {
		idx += start - 1;
		arr[idx] = val;
		for(int i = idx / 2; i > 0; i /= 2) {
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
		}
	}
	
	private static long getSum(long left, long right) {
		left += start - 1;
		right += start - 1;
		long sum = 0;
		while(left <= right) {
			if(left % 2 == 1) {
				sum += arr[(int)left];
				left++;
			}
			if(right % 2 == 0) {
				sum += arr[(int)right];
				right--;
			}
			left /= 2;
			right /= 2;
		}
		return sum;
	}
}