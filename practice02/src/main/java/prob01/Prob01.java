package prob01;

import java.util.Scanner;

public class Prob01 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in  );
		final int[] MONEYS = { 50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1 };
		
		System.out.println("금액: ");
		int n = scanner.nextInt();
		
		int cnt;
		for(int i = 0 ; i < MONEYS.length; i++){

			cnt = 0;
			cnt = n / MONEYS[i];
			if( cnt == 0) {
				continue;
			}
			else {
				n -= MONEYS[i]*cnt;
				System.out.println(MONEYS[i] + "원 :" + cnt + "개");
			}
			
		}
				
		scanner.close();
 	}
}