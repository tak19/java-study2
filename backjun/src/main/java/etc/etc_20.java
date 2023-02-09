package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class etc_20 {
	static StringBuilder sb = new StringBuilder();
	static int n;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		//n 자리 소수는 -> 2, 3, 5, 7로 시작해야함 
		int[] start = {2, 3, 5, 7};
		for(int i = 0 ; i < 4 ; i++) {
			plusCnt( start[i] , 1);
		}
		System.out.println(sb);
		
	}

	//자릿 수 늘려 주면서 소수 체크
	private static void plusCnt(int num, int cnt) {
		if( cnt == n ) {
			sb.append(num).append("\n");
			return;
		}
		num *= 10;
		for(int i = 1 ; i < 10; i++) {
			if( primeCk(num+i) ) {
				plusCnt( num+i , cnt + 1);
			}
		}
		
	}

	private static boolean primeCk(int prime) {
		//나누어 떨어지는 수 있으면 false 반환
		for(int i = 2; i <= Math.sqrt(prime) ; i++) {
			if( prime % i == 0 ) {
				return false;
			}
		}
		
		return true;
	}


	
}

