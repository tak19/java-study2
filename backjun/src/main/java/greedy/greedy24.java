package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class greedy24 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		//배열 입력
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int result = 0;
		for(int i = N-1 ; i > 0 ; i--) {
			int now = arr[i];
			int pre = arr[i-1];
			//앞 점수가 더 크다면 감소 시켜줌
			while( now <= pre ) {
				pre--;
				result++;
			}
			//변경된 점수 삽입
			arr[i-1] = pre;
		}
		
		System.out.println(result);
		
	}
}