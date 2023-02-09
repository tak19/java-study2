package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class etc_20 {

	static boolean[] prime;
	static List<Integer> plist = new ArrayList<Integer>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int max = (int) Math.pow(10, n);
		prime = new boolean[max];
		primeCk(max);
		prime[1] = prime[0] = true;
		StringBuilder sb = new StringBuilder();
		
		//신기한지 판단 -> 안에 자리 수가 소수인지는 boolean으로 보면될듯
		for(int i = 0 ; i < plist.size() ; i++) {
			
			boolean ck = false;
			int a = plist.get(i);
			//나눠가며 판단
			for(int j = 1 ; j <= n-1 ; j++) {
				int sub = (int) Math.pow(10, j);
				if( prime[  a/sub   ] ) { //소스가 아니라면
					ck = true;
					break;
				}
			}
			if( !ck ) {
				sb.append(a).append("\n");
			}
			
			
		}
		
		System.out.println(sb);
		
	}

	//소수 판별하기 --> 아라토무시기 체
	private static void primeCk(int max) {
		
		for(int i = 2 ; i * i <= max ; i++) {
			if( !prime[i] ) {
				for(int j = i*i ; j < max ; j += i) {
					//System.out.println("j :" + j);
					prime[j] = true;
				}
			}
			
		}
		//n 자릿수의 소수가 다 넣어줌
		for (int i = max/10; i < max; i++) {
			if (!prime[i])
				plist.add(i);
		}
		
	}
	
}

