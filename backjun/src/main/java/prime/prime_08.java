package prime;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class prime_08 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//총 링의 개수
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		//링 마다 크기 입력
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		int first = arr[0];
		for(int i = 1 ; i < n ; i++) {
			int div = gcd(first,arr[i] % first );
			sb.append(first/div).append("/").append(arr[i]/div).append("\n");
		}
		System.out.println(sb);
		
	}

	private static int gcd(int a, int b) {
		if( b == 0 ) {
			return a;
		}
		return gcd( b , a % b  );
	}
}