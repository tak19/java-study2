package prime;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class prime_09 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(br.readLine());
		//가로수 길이 입력받기
		int[] arr = new int[n];
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		//거리 차 입력 배열
		int[] dis = new int[n-1];
		int g = 0; // 거리간 최대 공약수를 저장
		for(int i = 0 ; i < n-1 ; i++) {
			dis[i] = arr[i+1] - arr[i];
			g = gcd(g,dis[i]);
		}
		//전체 간격 수에서 최대 공약수로 나눔 - 거기다 +1해서 나무의 개수 파악
		System.out.println( ((arr[n-1] - arr[0]) / g+1) - arr.length);
	}

	private static int gcd(int a, int b) {
		if( b == 0 ) {
			return a;
		}else {
			return gcd( b , a % b );
		}
	}
}