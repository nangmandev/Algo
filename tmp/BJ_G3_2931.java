package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G3_2931 {
	static int R, C;
	static char[][] map;
	static boolean up, down, left, right;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			String temp = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = temp.charAt(j);
			}
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(checkBlock(i, j)) {
					changeBlock(i, j);
					return;
				}
			}
		}
	}
	
	public static boolean checkBlock(int y, int x) {
		if(map[y][x] == '.') {
			// 윗부분
			if(y - 1 >= 0 && (map[y - 1][x] == '|' || map[y - 1][x] == '+' || map[y - 1][x] == '1' || map[y - 1][x] == '4')) up = true;
			// 아랫부분
			if(y + 1 < R && (map[y + 1][x] == '|' || map[y + 1][x] == '+' || map[y + 1][x] == '2' || map[y + 1][x] == '3')) down = true;
			// 왼쪽
			if(x - 1 >= 0 && (map[y][x - 1] == '-' || map[y][x - 1] == '+' || map[y][x - 1] == '1' || map[y][x - 1] == '2')) left = true;
			// 오른쪽
			if(x + 1 < C && (map[y][x + 1] == '-' || map[y][x + 1] == '+' || map[y][x + 1] == '3' || map[y][x + 1] == '4')) right = true;
			if(up || down || left || right) return true;
		}
		return false;
	}
	
	public static void changeBlock(int y, int x) {
		//char result = 'a';
		y++; x++;
		if(up && down && left && right) System.out.println(y + " " + x + " " + '+');
		else if(up && down) System.out.println(y + " " + x + " " + '|');
		else if(left && right) System.out.println(y + " " + x + " " + '-');
		else if(right && down) System.out.println(y + " " + x + " " + '1');
		else if(up && right) System.out.println(y + " " + x + " " + '2');
		else if(left && up) System.out.println(y + " " + x + " " + '3');
		else if(left && down) System.out.println(y + " " + x + " " + '4');
		
		//System.out.println(y + " " + x + " " + result);
	}
}
