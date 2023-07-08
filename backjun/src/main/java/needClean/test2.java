package needClean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test2 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int all = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		int cal = 0;
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			int price = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			
			cal += (price * cnt);
			
		}
		if( all == cal ) {
			System.out.println("Yes");
		}else {
			System.out.println("No");
		}
		
	}
}