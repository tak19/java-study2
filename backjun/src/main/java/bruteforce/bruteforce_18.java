package bruteforce;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bruteforce_18 {
	static int bigger,smaller;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//전체 인원 수
		int n = Integer.parseInt(st.nextToken());
		//지민이와 한수 숫자
		int k = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int result = 1;
		//큰 번호를 입력받음
		changeNum(k,m);
		while( ckShort(bigger,smaller) ) {
			bigger = downNum(bigger);
			smaller = downNum(smaller);
			result++;
		}
		System.out.println(result);
		
	}
	//나누어 떨어지는 수는 그대로 return 아니면 +1  후 리턴
	public static int downNum(int a) {
		if( a % 2 == 1 ) {
			return ( a / 2 ) + 1;
		}
		return ( a / 2 );
	}
	
	//인접 숫자인지 확인
	public static boolean ckShort(int a , int b) {
		if( (a - b) == 1 && ( a % 2 == 0) ) {
			return false;
		}
		return true;
	}
	
	//큰 수를 저장
	public static void changeNum(int k, int m) {
		if( k > m ) {
			bigger = k;
			smaller = m;
		}else {
			bigger = m;
			smaller = k;
		}
	}
	
}