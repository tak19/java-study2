package etc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class etc_28 {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//수열의 길이와 원하는 합 입력받음
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		//수열 입력받기
		int[] arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		//수열 계산 시작
		int result = 0;
		int sum = 0;
		int index = 0;
		for(int i = 0 ; i < n ; i++) {
			//계속 합을 누적함
			sum += arr[i];
			//만약 목표값 m 을 넘는다면 m 보다 작거나 같아질때까지 끝에서 감소시킴
			while( sum > m ) {
				sum -= arr[index++];
			}
			//목표값 도달시 개수 증가 시켜주고 시작 인덱스 값을 빼고 인덱스 늘려줌
			if( sum == m ) {
				result++;
				sum -= arr[index++];
			}
			
		}
		System.out.println(result);
		
	}
}