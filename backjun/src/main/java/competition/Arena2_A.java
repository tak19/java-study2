package competition;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Arena2_A {
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());
		int sum = 0;
		int result = 1;
		for(int i = 1; i <= N ; i++) {
			sum += i;
		}
		result = (int) Math.pow(sum, 2);
		sb.append(sum).append("\n").append(result).append("\n").append(result);
		System.out.println(sb);
	}
}