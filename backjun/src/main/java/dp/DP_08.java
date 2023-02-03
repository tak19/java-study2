package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DP_08 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		while(true) {
			//5kg 봉다리로 다 들 수 있음 이게 최선임!
			if( n % 5 == 0) {
				cnt += (n / 5);
				System.out.println(cnt);
				return;
			}
			else {
				//5로 안 나눠지면 3키로 하나 들고가고 생각
				n -= 3;
				cnt++; 
			}
			if( n < 0 ) {
				System.out.println(-1);
				return;
			}
		}
		
		
		
		
	}
}
