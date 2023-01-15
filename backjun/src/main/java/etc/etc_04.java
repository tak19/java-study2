package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class etc_04 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		//자기 자신의 경우는 고정이기 때문에 cnt 1로 초기화
		int cnt = 1;
		int start = 1;
		int end = 1;
		int sum = 1;
		
		//투포인터 이용
		while(end !=n) {
			if(sum == n) {
				cnt++;
				end++;
				sum += end;
			}else if(sum > n) {
				sum -= start;
				start++;
			}else {
				end++;
				sum += end;
			}
			
		}

		//숫자를 반으로 나눠서 탐색
//		// 연속된 수들의 합이므로 적어도 n의 반보다는 낮은 수;
//		int sub = (int) Math.ceil(n / 2) + 1;
//		if (n > 2) {
//			for (int i = sub; i >= 0; i--) {
//				int sum = 0;
//				for (int j = i; j >= 0; j--) {
//					sum += j;
//					if (sum == n) {
//						cnt++;
//						break;
//					} else if (sum > n) {
//						break;
//					}
//				}
//			}
//		}

		System.out.println(cnt);

	}

}
