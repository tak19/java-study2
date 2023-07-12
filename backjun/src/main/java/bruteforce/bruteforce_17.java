package bruteforce;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bruteforce_17 {
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[10001];
		
		//10000까지 알아봄
		for(int i = 0 ; i <= 10000 ; i++) {
			int tem = i;
			int sum = tem;
			//자기 숫자 카운팅해줌
			arr[tem]++;
			//자리 수 연산해서 카운팅 시켜줌
			while( tem != 0 ) {
				sum += (tem % 10);
				tem /= 10;
			}
			if( sum <= 10000 ) {
				arr[sum]++;				
			}
			
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= 10000 ; i++) {
			if( arr[i] == 1 ) {
				sb.append(i).append("\n");
			}
		}
		System.out.println(sb);
		
		
	}
}