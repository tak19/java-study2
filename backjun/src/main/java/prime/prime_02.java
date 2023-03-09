package prime;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class prime_02 {
	static boolean[] prime;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long start = Long.parseLong(st.nextToken()); // 시작수
		long k = Long.parseLong(st.nextToken()); // 끝수


		long range = (long)Math.sqrt(k);
		long result = 0;

		List<Long> list = new ArrayList<>();
		//n의 제곱근까지만 
		boolean[] ck = new boolean[10_000_001];
		ck[1] = true;
		for(long i = 2l ; i <=range ; i++) {
			if( !ck[(int)i] ) {
				list.add( (Long) i);
				for(long j = i; j <= range ; j += i) {
					ck[(int)j] = true;
				}
			}
		}

		//판별하기 -> 구한 소수 크기만큼
		for(int i = 0 ; i < list.size() ; i++) {
			long tem = list.get(i);
			while( (double) list.get(i) <= (double)k / (double) tem ) {
				if( (double) list.get(i) >= (double)start / (double) tem ) {
					result++;
				}
				tem = tem * list.get(i);
			}


		}
		System.out.println(result);
	}

}