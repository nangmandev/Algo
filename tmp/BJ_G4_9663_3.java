package algo;

import java.util.Scanner;

public class BJ_G4_9663_3 {
	public static int N, col[], ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		col = new int[N + 1];
		ans = 0;
		
		setQueen(1);
		
		System.out.println(ans);
		
	}
	
	private static void setQueen(int row) {
		
		if(!isAvailable(row - 1)) {
			return;
		}
		
		if(row > N) {
			ans = ans + 1;
			return;
		}
		
		for(int c = 1; c <= N; c++) {
			col[row] = c;
			setQueen(row + 1);
		}
	}
	
	private static boolean isAvailable(int row) {
		for(int i = 1; i < row; i++) {
			if(col[i] == col[row] || row - i == Math.abs(col[row] - col[i])) {
				return false;
			}
		}
		return true;
	}
}
