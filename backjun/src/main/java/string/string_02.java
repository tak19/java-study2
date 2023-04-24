package string;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class string_02 {
	static int result;
	static int[] ck;
	static String s;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		int len = s.length();

		//길이만큼 반복
		int cnt = 0;
		for(int i = 0 ; i < len ; i++) {
			//펠린드롬 확인
			if( ck(s.substring(i)) ) {
				break;
			}
			//길이 1증가
			cnt++;
		}
		
		System.out.println(len + cnt);
	}

	//펠린드롬인지 체크
	private static boolean ck(String palin) {
		int size = palin.length()-1;
		int re = palin.length() / 2;
		//펠린트롬 체크
		for(int i = 0 ; i < re ; i++) {
			if( palin.charAt(i) != palin.charAt(size-i) ) {
				return false;
			}
		}
		return true;
		
	}
}