package string;
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
public class string_04 {
	static String S,T;
	static StringBuffer sb;
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
		S = br.readLine();
		T = br.readLine();

		making(T);
		System.out.println(result);
	}
	//단어 만들기
	private static void making(String s2) {
		if( S.equals(s2) ) {
			result = 1;
			return;
		}
		int len = s2.length()-1;
		char c = s2.charAt(len);
		if( len == 0 ) {
			return;
		}
		
		String tem = s2.substring(0,len);
		if( c == 'A') {
			making(tem);
		}else if(c == 'B') {
			sb = new StringBuffer(tem);
			making(sb.reverse().toString());
		}
		
	}
}