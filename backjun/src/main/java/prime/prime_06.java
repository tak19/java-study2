package prime;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
문자열의 뒤에 A를 추가한다.
문자열을 뒤집고 뒤에 B를 추가한다.
 */
public class prime_06 {
	static String S,T;
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		long g = cal(a,b%a);
		StringBuilder sb = new StringBuilder();
		//System.out.println(g);
		for(int i = 0 ; i < g ; i++) {
			sb.append(1);
		}
		System.out.println(sb);
	}
	//최대공약수 구함
	private static long cal(long a, long b) {
		if( b == 0 ) {
			return a;
		}
		return cal(b,a%b);
	}
}