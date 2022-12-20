package prob3;

import java.util.Scanner;

public class Prob3 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("숫자를 입력하세요: ");
		int n = scanner.nextInt();
		int sum = 0; // 누적합

		if (n % 2 == 0) {
			while (n > 0) {
				sum += n;
				n -= 2;
			}
		} else if (n % 2 == 1) {
			while (n > 0) {
				sum += n;
				n -= 2;
			}
		}
		System.out.println("결과값: " +  sum);

		/* 코드 작성 */

		scanner.close();
	}
}
