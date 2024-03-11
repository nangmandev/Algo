package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class abcd {
	static char[] src = {'a', 'b', 'c', 'd'};
	
	public static void main(String[] args) throws IOException {
		
		permu(0, new char[3], new boolean[4]);
	}
	
	// 재귀를 이용해서 3개를 고르는 순열
	
	public static void permu(int nth, char[] tempArr, boolean[] visited) {
		if(nth == tempArr.length) {
			System.out.println(Arrays.toString(tempArr));
			return;
		}
		
		for(int i = 0; i < src.length; i++) {
			if(visited[i] == false) {
				tempArr[nth] = src[i];
				visited[i] = true;
				permu(nth + 1, tempArr, visited);
				visited[i] = false;
			}
		}
	}
}