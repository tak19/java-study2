package bruteforce;

import java.util.Scanner;

public class bruteforce_01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();

		int cnt = 0;
		int ck = 0;
		if (n >= 100) {
			cnt = 99;
			for (int i = 100; i <= n; i++) {
				ck = 0;
				char[] arr = String.valueOf(i).toCharArray();
				 int mul = arr[0] - arr[1];
				for (int j = 1; j < arr.length - 1; j++) {
					if (mul != arr[j] - arr[j + 1]) { // 차가 같지 않은 경우임
						ck = 1;
						break;
					}

				}
				if (ck != 1) {
					cnt++;
				}

			}
		} else {
			cnt = n;
		}

		System.out.println(cnt);
	}

}
