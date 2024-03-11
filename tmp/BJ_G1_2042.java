/**

 @author 한규준
 @since 2023-08-08
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_2042.java
 @youtube
 @performance 110484KB, 520ms
 @category 세그먼트 트리
 @note

세그먼트 트리로 업데이트/구간합 구하기


 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G1_2042 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int treeHeight = (int)Math.ceil(Math.log(N) / Math.log(2));
		int start = (int)Math.pow(2, treeHeight);
		long[] segmentTree = new long[start * 2];

		for(int i = start; i < start + N; i++){
			segmentTree[i] = Long.parseLong(br.readLine());
		}
		// 단말노드 초기화 완료

		for(int i = segmentTree.length - 1; i >= 1; i--){
			segmentTree[i / 2] += segmentTree[i];
		}
		// 전체트리 초기화 완료

		int a, b;
		long c;
		for(long i = 0; i < K + M; i++){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Long.parseLong(st.nextToken());

			if(a == 1){
				changeValue(segmentTree, b, c);
			} else if(a == 2){
				bw.write(partSum(segmentTree, b, c) + "\n");
			}
		}
		bw.flush();
		bw.close();
	}

	private static void changeValue(long[] segmentTree, int index, long num){
		int reIdx = index + segmentTree.length / 2 - 1;
		long diff = num - segmentTree[reIdx];

		for(int i = reIdx; i >= 1; i /= 2){
			segmentTree[i] += diff;
		}
	}
	// 트릐 값 변경시 부모노드 싹변경

	private static long partSum(long[] segmentTree, int start, long end){
		start += segmentTree.length / 2 - 1;
		end += segmentTree.length / 2 - 1;
		long sum = 0;
		while(start <= end){
			if(start % 2 == 0){
				start /= 2;
			} else {
				sum += segmentTree[start];
				start = (start + 1) / 2;
			}

			if(end % 2 == 0){
				sum += segmentTree[(int)end];
				end = (end - 1) / 2;
			} else {
				end /= 2;
			}
		}
		return sum;
	}
	// 합 구하기
}
