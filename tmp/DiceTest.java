/**

@author 한규준
@since 2023-08-01
@see
@git
@youtube
@performance
@category #
@note

*/


package algo;

import java.util.Arrays;
import java.util.Scanner;

public class DiceTest {
	static int N, numbers[];
	static boolean[] isSelected;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	N = sc.nextInt();
    	int M = sc.nextInt();
    	numbers = new int[N];
    	switch(M) {
    	case 1:
    		dice1(0);
    		break;
    	case 2:
    		isSelected = new boolean[7];
    		dice2(0);
    		break;
    	}
    }
    
    // 중복된 주사위 눈 가능
    private static void dice1(int cnt) {
    	if(cnt == N) {
    		System.out.println(Arrays.toString(numbers));
    		return;
    	}
    	for(int i = 1; i <= 6; i++) {
    		numbers[cnt] = i;
    		dice1(cnt + 1);
    	}
    }
    
    // 중복 불가
    private static void dice2(int cnt) {
    	if(cnt == N) {
    		System.out.println(Arrays.toString(numbers));
    		return;
    	}
    	for(int i = 1; i <= 6; i++) {
    		if(isSelected[i]) continue;
    		numbers[cnt] = i;
    		isSelected[i] = true;
    		dice2(cnt + 1);
    		isSelected[i] = false;
    	}
    }
}
