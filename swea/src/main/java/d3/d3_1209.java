package d3;

import java.util.Scanner;

public class d3_1209 {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);

		for (int z = 0; z < 10; z++) {
			//각 테스트 케이스 번호 출력
			System.out.print("#" + sc.nextInt() + " ");
			int[][] arr = new int[100][100];
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			int max = 0;
			int total3 = 0;
			int total4 = 0;
			int cnt = 100;
			// 행 방향으로 계산
			for (int i = 0; i < 100; i++) {
				int total1 = 0;
				int total2 = 0;
				for (int j = 0; j < 100; j++) {
					total1 += arr[i][j]; //행
					total2 += arr[j][i]; //열
					
					if( j == i ) { //오른대각
						total3 += arr[i][j];
					}
					
				}
				total4 += arr[i][--cnt]; //왼 대각
				max = Math.max(max, Math.max(total1, total2));
				max = Math.max(max, Math.max(total3, total4));
				
			}
			System.out.println(max);

		}

	}
}