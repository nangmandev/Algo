package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SWEA_D3_1225 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tc;
		String[] temp;
		Deque<Integer> deq = new ArrayDeque<Integer>();
		int count;
		
		for(int T = 1; T <= 10; T++) {
			count = 1;
			tc = Integer.parseInt(br.readLine());
			temp = br.readLine().split(" ");
			
			for(int i = 0; i < 8; i++) {
				deq.add(Integer.parseInt(temp[i]));
			}
			
			while(true) {
				int tmpInt = deq.pollFirst();
				if(tmpInt - count > 0) {
					deq.addLast(tmpInt - count);
				} else {
					deq.addLast(0);
					break;
				}
				count = count % 5 + 1;
			}
			bw.write("#" + T + " ");
			for(int i = 0; i < 8; i++) {
				bw.write(deq.pollFirst() + " ");
			}
			bw.write("\n");
			deq.clear();
		}
		
		bw.flush();
		bw.close();
	}
}
