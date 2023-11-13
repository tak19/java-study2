package dp;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DP_13 {
	static int N,result;
	static int[] arr,dis;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		System.out.println( (N % 7 == 0 || N % 7 == 2) ? "CY" : "SK" );
		
	}
}