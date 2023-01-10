package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class greedy05 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long n = Long.parseLong(br.readLine()); //보석 무게
		int cnt = 0 ; //들고갈 보석 개수
		int sub = 2;
		
		StringBuffer sb = new StringBuffer();
		
		//0될때까지 소수로 나눌꺼야
		while (n != 1) {
			
			if (sub >= 1000000) {
				sb.append(n);
				cnt++;
				break;
			}
			
			
			while (n % sub == 0) {// 나누어 떨어져야함
				n /= sub; // 나머지 보석무게 계산을 위함
				cnt++; // 보석수 증가
				sb.append(sub + " "); // 보석 무게 저장
				
			}

			sub++; // 소수든 아니든 보석(소인수)의 무게 증가
		}

		System.out.println(cnt);
		System.out.println(sb);

	}
	
	//소수 판별 (소수: 1과 자기 자신만이 나눠지는 수) ->> 1~ n제곱근 까지 범위 설정 이유..
	//     -> 즉, 2부터 시작하여 반정도만 가면됨,굳이 끝까지 나눌 필요없음
	public static boolean isPrime(int n) {
		for (int i = 2; i<=(int)Math.sqrt(n); i++) {
	      if (n % i == 0) {
	          return false;
	      }
		}
		return true;
	}

}
