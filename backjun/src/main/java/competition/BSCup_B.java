package competition;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BSCup_B {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 1 ; i <= n ; i++) {
			sb.append("Case #").append(i).append(": ");
			String s = "";
			int cnt = Integer.parseInt(br.readLine());
			if( cnt <= 25 ) {
				s = "World Finals";
			}else if( cnt <= 1000 ) {
				s = "Round 3";
			}else if( cnt <= 4500 ) {
				s = "Round 2";
			}else {
				s = "Round 1";
			}
			sb.append(s).append("\n");
		}
		System.out.println(sb);
	}
}