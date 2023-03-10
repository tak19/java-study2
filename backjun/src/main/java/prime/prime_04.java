package prime;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prime_04 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken()); // 최소
		long max = Long.parseLong(st.nextToken()); // 최대

		//필요 수만큼
		boolean[] ck = new boolean[(int)(max-min+1)];

		for(long i = 2 ; i*i <=max ; ++i) {
			long pow = i*i; //제곱수 
			long sIndex = min / pow;
			
			if(min % pow != 0) {
				sIndex++; // 중복 방지 --> 즉 나머지가 있는경우 값이 안겹치게하기 위함
			}
			for(long j = sIndex; pow * j <= max ; ++j) {
				ck[(int) (j*pow - min) ] = true;
			}
		}
		
		long result = 0; // 결과값
		//판별하기 -> 구한 소수 크기만큼
		for(int i = 0 ; i <= max - min ; ++i) {
			if( !ck[i] ) {
				result++;
			}
		}
		System.out.println(result);
	}

}