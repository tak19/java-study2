package d2;

import java.util.Scanner;

public class d2_1974 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			boolean ck = true; //확인용 불리언 변수 flase 면 스도쿠 성립x
			//수도쿠 판 배열을 생성후 입력받음
			int[][] arr = new int[9][9];
			for(int i = 0 ; i < 9 ; i ++) {
				for(int j = 0 ; j < 9 ; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			ck = area(arr,ck);
			ck = row(arr,ck);
			ck = col(arr,ck);
			
			if(ck) {
				System.out.println("#" + test_case + " " + 1);
			}else {
				System.out.println("#" + test_case + " " + 0);
			}
			
		}
		
	}

	private static boolean area(int[][] arr, boolean ck) {
		//3x3 탐색
		for(int i = 0 ; i <= 6 ; i += 3) {			
			for(int j = 0 ; j <= 6 ; j+= 3) {
				int[] num = new int[9];
				// 0 - 0~2, 3~5, 6~8
				for(int a = i ; a < i+3 ; a++) {
					for(int b = j ; b < j+3 ; b++) {
						num[ arr[a][b]-1 ]++;
					}
				}
				for (int one : num){
					if(one != 1) {
						return false;
					}
				}
				
			}
			
		}
		return true;
	}

	private static boolean col(int[][] arr, boolean ck) {
		if(!ck) {
			return ck;
		}
		
		int[] num = new int[9];
		//열 탐색
		for(int i = 0 ; i < 9 ; i ++) {
			for(int j = 0 ; j < 9 ; j++) {
				num[ arr[j][i] -1 ]++;  
			}
			//스도쿠 숫자를 인덱스로 활용한 num 배열 개수가 안맞으면 성립x
			for(int j = 0 ; j < 9 ; j++) {
				if(num[j] != i +1) {
					return false;
				}
			}
		}
		
		return true;
		
	}

	private static boolean row(int[][] arr, boolean ck) {
		if(!ck) {
			return ck;
		}
		
		int[] num = new int[9];
		//행 탐색
		for(int i = 0 ; i < 9 ; i ++) {
			for(int j = 0 ; j < 9 ; j++) {
				num[ arr[i][j] -1 ]++;  
			}
			//스도쿠 숫자를 인덱스로 활용한 num 배열 개수가 안맞으면 성립x
			for(int j = 0 ; j < 9 ; j++) {
				if(num[j] != i +1) {
					return false;
				}
			}
		}
		
		return true;
		
		
	}
	
}
