package bronze;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class bronze_14 {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		int D = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		int sum = 0;
		//고기 온도가 영하일떄 0도일떄 영상일때에따라 다른 결과값을 저장한다.
		if(A < 0) {
			sum = (Math.abs(A) * C) + D + (B * E);
		}else if(A == 0){
			sum = D + (B * E);
		}else {
			sum = (B - A) * E;
		}
		
		System.out.println(sum);
	}

}