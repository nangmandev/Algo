package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_P4_10999 {
	static int N, M, K, height, start;
	static long[] arr, comp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		height = (int)Math.ceil(Math.log(N) / Math.log(2));
		start = (int)Math.pow(2, height);
		arr = new long[start * 2];
		comp = new long[start * 2];

		for(int i = start; i < start + N; i++){
			arr[i] = Long.parseLong(br.readLine());
		}
		for(int i = start - 1; i > 0; i--){
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
		}

		// 초기화 끝

		int a, b, c;
		long d;
		for(int i = 0; i < M + K; i++){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			if(a == 1){
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				d = Long.parseLong(st.nextToken());
				update(b, c, d);
				continue;
			}
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			bw.write(getSum(b, c) + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static void update(int left, int right, long val){
		left += start - 1;
		right += start - 1;
		long compen = 1;
		while(left <= right){
			// 우측으로 이동할 것이기 때문에 현재 노드의 부모노드부터 루트노드를 미리 갱신
			// 노드가 올라가면서 갱신해야 할 값은 현재 값의 두배씩 커질 것이므로 보정을 해준다.
			if(left % 2 == 1) updateCompensator(left++, val, compen);
			if(right % 2 == 0) updateCompensator(right--, val, compen);
			left /= 2;
			right /= 2;
			compen *= 2;
		}
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(comp));
	}

	private static void updateCompensator(int idx, long val, long compen){
		comp[idx] += val;
		idx /= 2;
		while(idx != 0){
			arr[idx] += val * compen;
			idx /= 2;
		}
	}

	private static long getSum(int left, int right){
		left += start - 1;
		right += start - 1;
		long result = 0;
		long compen = 1;
		while(left <= right){
			// 벗어나는경우 보정치값을 더해준다.
			if(left % 2 == 1) result += getCompensator(left++, compen);
			if(right % 2 == 0) result += getCompensator(right--, compen);
			left /= 2;
			right /= 2;
			compen *= 2;
		}
		return result;
	}

	private static long getCompensator(int idx, long compen){
		long ret = arr[idx];
		while(idx != 0){
			ret += comp[idx] * compen;
			idx /= 2;
		}
		return ret;
	}
}
