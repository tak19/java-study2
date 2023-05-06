package bruteforce;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
//9,876,543,210
public class bruteforce_15 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long result = 0;
		
		//10보다 작거나 같으면 해당 값이 결과 값임
		if( N <= 10 ) {
			System.out.println(N);
		}else {
			//감소하는 수 찾기
			int num = 10;
			long start = 11;
			while( num <= 1_000_000 ) {

				//감소하는 수인지 판단함
				if( ck(start) ) {
					num++; //감소하는 수 증가 시켜줌
				}
				//해당 번째의 감소하는 수를 찾는다면
				if( num == N ) {
					result = start;
					break;
				}
				 
				
				start++; //수 증가
			}
			result = result == 0 ? -1 : result;
			System.out.println(result );
		}
		
		
	}
	//감소하는 수 인지 판단함
	private static boolean ck(long start) {
		String s = start+"";
		for(int i = s.length()-1 ; i >= 1 ; i--) {
			int be = s.charAt(i) - '0';
			int fi = s.charAt(i-1) - '0';

			//뒤에 수가 더 크다면
			if( be >= fi ) {
				return false;
			}
			
		}
		return true;
	}
}