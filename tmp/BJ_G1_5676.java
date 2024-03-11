/**

 @author 한규준
 @since 2023-08-28
 @see https://www.acmicpc.net/problem/5676
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_5676.java
 @youtube
 @performance 105028KB, 668ms
 @category 세그먼트 트리
 @note

세그먼트 트리 구현
 입력 EOF 잘 받을것
 1, 0, -1로 구분해서 받기(오버/언더플로우)

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G1_5676 {
	static int N, K, height, start;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		String input;

		while((input = br.readLine()) != null) {
			st = new StringTokenizer(input);
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			height = (int) Math.ceil(Math.log(N) / Math.log(2));
			start = (int) Math.pow(2, height);
			arr = new int[start * 2];
			Arrays.fill(arr, 1);

			st = new StringTokenizer(br.readLine());
			for (int i = start; i < start + N; i++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp > 0) arr[i] = 1;
				else if (temp < 0) arr[i] = -1;
				else arr[i] = 0;
			}

			for (int i = start - 1; i > 0; i--) {
				arr[i] = arr[i * 2] * arr[i * 2 + 1];
			}

			char a;
			int b, c;
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				a = st.nextToken().charAt(0);
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				if (a == 'C') update(b, c);
				else {
					int temp = getMul(b, c);
					if (temp == 0) bw.write("0");
					else if (temp > 0) bw.write("+");
					else if (temp < 0) bw.write("-");
				}
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
	
	private static void update(int idx, int val) {
		idx += start - 1;
		if(val == 0) arr[idx] = 0;
		else if(val > 0) arr[idx] = 1;
		else arr[idx] = -1;
		for(int i = idx / 2; i > 0; i /= 2) {
			arr[i] = arr[i * 2] * arr[i * 2 + 1];
		}
	}
	
	private static int getMul(int left, int right) {
		left += start - 1;
		right += start - 1;
		int rtn = 1;
		while(left <= right) {
			if(left % 2 == 1) rtn *= arr[left++];
			if(right % 2 == 0) rtn *= arr[right--];
			left /= 2;
			right /= 2;
		}
		return rtn;
	}
}