package competition;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BSCup_A {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		//범위 구함
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		//k와 범위
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		int result = 0;
		for(int i = a ; i <= b ; i++) {
			if( Math.abs(i-k) <= x ) {
				result++;
			}
		}
		
		if( result > 0 ) {
			System.out.println(result);
		}else {
			System.out.println("IMPOSSIBLE");
		}
	}
}