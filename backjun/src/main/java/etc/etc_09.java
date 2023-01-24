package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class etc_09 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr= new int[n+1];
		Stack<Integer> s = new Stack<>();
		//수열을 입력받음!!
		for(int i = 1; i <= n ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		//0을 대입 해둠!
		int cnt = 0;
		//s.add(cnt);

		StringBuilder sb = new StringBuilder();
		//수열 만들 수 있는 지확인
		for(int i = 1; i <= n ; i++) {
			int now = arr[i];
			
			//현재 스택값이 출력값보다 작거나 같다면 스택에 값을 넣어줌!!
			if( cnt <= now ) {
				while( cnt < now) { //제일 앞에 값이 출력값보다 작으면 삽입 시작.
					sb.append("+\n");
					s.add(++cnt);
				}
				if( s.peek() == now ) {
					sb.append("-\n");
					s.pop();
					continue;
				}
				
			}else { //출력값 < 현재 스택값 --> 스택에서 값을 찾는다.
				if( s.peek() > now ) { //스택 값 > 출력 이라면 불가 
					System.out.println("NO");
					return;
				}else {
					sb.append("-\n");
					s.pop();
					continue;
				}
				
				
			}

		}
		System.out.println(sb);

	}
}
