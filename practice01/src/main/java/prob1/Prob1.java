package prob1;

import java.util.Scanner;

public class Prob1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in );
		
		/* 코드 작성 */
		System.out.print("수를 입력하세요: ");
		int n = scanner.nextInt();
		scanner.close();
		
		
		if(n % 3 == 0) {
			System.out.println("3의 배수 입니다");
		}else {
			System.out.println("3의 배수 아니다");
		}
		
	}
}
