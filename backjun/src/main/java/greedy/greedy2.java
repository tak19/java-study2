package backjun;

import java.util.Scanner;

public class greedy2 {
	static final int max = 1000;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//내가 낸돈 - 산 물건의 가격 = 거스름돈
		int n = max - sc.nextInt();
		sc.close();

		//total => 거스름돈의 갯수
		int total = 0;
		//500엔, 100엔, 50엔, 10엔, 5엔, 1엔이 충분히 있고		
		int[] arr = { 500, 100, 50, 10, 5, 1 };
		for(int i = 0 ; i < arr.length; i++) {
			total += n / arr[i];
			n %= arr[i];
			if(n == 0)
				break;
		}
		System.out.println(total);
		
	}

}
