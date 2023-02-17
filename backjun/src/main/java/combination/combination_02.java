package combination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class combination_02 {
	private static StringBuilder sb = new StringBuilder();
	static  BigInteger[][] arr;

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //전체 수
		int m = Integer.parseInt(st.nextToken()); //뽑는 수
		
		arr = new BigInteger[n+1][m+1];
		for(int i = 0 ; i <= n ; i++) {
			for(int j = 0; j <= m ; j++) {
				arr[i][j] = BigInteger.valueOf(0);
			}
		}
		combi(n,m);
		System.out.println(arr[n][m]);

	}

	private static BigInteger combi(int n, int m) {
		if( arr[n][m].compareTo( BigInteger.valueOf(0) ) == 1 )  {
			return arr[n][m];
		}
		if( n == m || m == 0 ) {
			return arr[n][m] = BigInteger.valueOf(1);
		}
		
		return arr[n][m] = combi(n-1,m-1).add(combi(n-1,m));
	}


}

