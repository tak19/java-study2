package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_12 {
	private static StringBuilder sb = new StringBuilder();
	static int N,R;
	static int[] numbers; //하나의 경우의 수 저장
	static int[] input; //집합
	private static boolean[] inSelected; //선택여부

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //전체 수
		R = Integer.parseInt(st.nextToken()); //뽑을 수

		input = new int[N];
		inSelected = new boolean[N];
		numbers = new int[R];

		for(int i = 0 ; i < N; i++) {
			input[i] = i+1;
		}
		Permu(0);

		System.out.println(sb);
	}
	//cnt: 뽑은 수의 개수
	private static void Permu(int cnt) {
		//기저 부분
		if ( cnt == R ) {
			for(int i = 0 ; i < R ; i++) {
				sb.append(numbers[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		//유도부분
		for(int i = 0 ; i < N ; i++ ) {
			//선택된 수이면 다음으로 넘어감
			if( inSelected[i] ) {
				continue;
			}
			//선택죄이 않았다면 input 배열에서 수 하나를 뽑는다.
			numbers[cnt] = input[i];
			inSelected[i] = true;
			//다음 수 뽑으러 가기
			Permu( cnt + 1 );
			// 사용했던 수에 대한 선택 되돌리기
			inSelected[i] = false;
		}
	}
}