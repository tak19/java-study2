package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class bruteforce_09 {
	static int N, result;
	static List<Character> operList;
	static List<Integer> numList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//연산자 입력받기
		numList = new ArrayList<>();
		operList = new ArrayList<>();
		N = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray();
		for(int i = 0 ; i < N ; i++) {
			//피연산자라면
			if( i % 2 == 0 ) {
				numList.add(arr[i] - '0');
			}else { //연산자 라면
				operList.add(arr[i]);
			}
		}
		result = Integer.MIN_VALUE;
		//현재 인덱스, 계산값, 
		dfs(0,numList.get(0));
		System.out.println(result);

	}
	//연산자 재귀 -> 현재를 괄호 or 다음 피연산자를 괄호로 구분하여 재귀
	private static void dfs(int index, int sum) {
		//연산 다했다면 --> 전체 길이 /2
		if( index >= (N / 2) ) {
			result = Math.max(result, sum);
			return;
		}
		//현재값 바로 갱신
		dfs( index+1, cal(index, sum, numList.get(index+1) ) );
		
		//뒤에 2개 괄호 칠 피연산자들이 남아있다면
		if( index + 2 <= operList.size()) {
			int back = cal(index+1, numList.get(index+1), numList.get(index+2) );
			dfs( index+2, cal(index, sum, back ) );
		}
		
	}
	//계산 결과 돌려줌
	private static int cal(int index, int front, int back) {
		int re = 0;
		switch (operList.get(index)) {
		case '+': {
			re = front + back; 
			break;
		}case '-': {
			re = front - back;
			break;
		}case '*': {
			re = front * back;
			break;
		}case '/': {
			re = front / back;
			break;
		}
		}
		return re;
	}
}


