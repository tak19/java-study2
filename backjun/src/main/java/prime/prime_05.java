package prime;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class prime_05 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		
		int range = (int)Math.sqrt(n); //전체 다 탐색할 필요가 없음
		long result = n;
		
		//오일러 피
		for(long i = 2 ; i <= range ; i++) { //제곱근까지만
			//소인수인지 확인 후결과값 갱신 --> 최대한 많이 소인수 분해 
			if( n % i == 0 ) { 
				result -= result/i;
				while(n%i==0) {
					n /= i;
				}
			}
		}
		if( n > 1 ) {
			result -= result / n;
		}
		
		System.out.println(result);
		
	}
}