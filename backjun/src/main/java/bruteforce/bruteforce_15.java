package bruteforce;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
//9,876,543,210
public class bruteforce_15 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[] arr = new long[1023];
		// 0-9 까지의 부분집합으로 계산함.

		for(int i = 1 ; i <= 1023 ; i++) {
			long num = 0;
			int tem_i = i;
			//차례대로 9부터 넣음 -- 감소하는 수를 만들기 위함
			for(int n = 9 ; n >= 0 ; n--) {
				if( tem_i % 2 == 1 ) {
					num = num * 10 + n; //해당 자리수를 뽑아 10진법으로 바로 전환
				}
				tem_i /= 2;
			}			
			arr[i-1] = num;
		}
		Arrays.sort(arr);
		
		
		if( N >= 1023 ) {
			System.out.println(-1);
		}else {
			System.out.println(arr[N]);
		}
		
	}
}