/**

 @author 한규준
 @since 2023-08-22
 @see https://www.acmicpc.net/problem/1517
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_P5_1517.java
 @youtube
 @performance 148264KB, 500ms
 @category 버블소트, 머지소트, 분할정복
 @note

1. 버블소트를 그대로 하면 시간초과입니다.(버블소트 O(N^2), N = 500000이므로 1초 내로 불가능)
2. 버블소트는 인덱스가 빠른 쪽에 있는 원소를 큰 수가 있는 곳까지 옮깁니다.
3. 머지소트를 사용해서, 작은 쪽 인덱스에 있는 큰 수를 옮기는 횟수를 구합니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_P5_1517 {
	static int N, arr[];
	static long count = 0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 초기화 완료
		
		// 병합정렬
		// 부분배열 크기가 1이 될때까지 분할
		mergeSort(0, N);
		
		System.out.println(count);
		
	}
	
	public static int[] mergeSort(int start, int end) {
		int mid = (start + end) / 2;
		// 배열의 크기가 1이 됨
		if(start == mid) {
			// System.out.print(arr[mid] + " ");
			// 배열을 넘김
			int[] temp = {arr[start]};
			return temp;
		}
		
		int[] arrA = mergeSort(start, mid);
		int[] arrB = mergeSort(mid, end);
		
		int[] mergeArr = new int[arrA.length + arrB.length];
		
		for(int i = 0, a = 0, b = 0; i < mergeArr.length; i++) {
			// 더 큰 인덱스쪽 배열의 원소가 더 크면 카운트를 해줘야한다.
			if(a == arrA.length) {
				mergeArr[i] = arrB[b++];
			}
			else if(b == arrB.length) {
				mergeArr[i] = arrA[a++];
			}
			else if(arrA[a] > arrB[b]) {
				mergeArr[i] = arrB[b++];
				count += (arrA.length - a);
			}
			else {
				mergeArr[i] = arrA[a++];
			}
		}
		//System.out.println(Arrays.toString(mergeArr));
		return mergeArr;
	}
}
