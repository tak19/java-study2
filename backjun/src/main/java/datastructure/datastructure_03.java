package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class datastructure_03 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();
		
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>(); //연산자 저장 스택
		//문자열 길이만큼 탐색
		for(int i = 0 ; i < arr.length ; i++) {
			//피연산자라면 출력에 추가
			if( 'A' <= arr[i] && arr[i] <= 'Z' ) {
				sb.append(arr[i]);
			}else if( arr[i] == '(' ) {
				//여는 괄호라면 그대로 입력 --> 스택에 집어넣음
				stack.add(arr[i]);
			}else if ( arr[i] == ')' ) {
				//닫는 괄호 만나면 여는 괄호까지 연산자 출력
				while( !stack.isEmpty() ) {
					if( stack.peek() == '(' ) {
						//여는 괄호 만나면 괄호 빼내고 반복 종료- 다음탐색
						stack.pop();
						break;
					}
					sb.append(stack.pop());
				}
				
			}else {
				//그냥 연산자를 만난다면 - 스택에 있는 연산자와 우선순위 비교 후 넣음
				while( !stack.isEmpty() && ckOper(stack.peek()) >= ckOper(arr[i]) ) {
					sb.append(stack.pop());
				}
				stack.add(arr[i]);
			}
		}
		//남은 연산자 뒤에 붙여넣기
		while( !stack.isEmpty() ) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
		
	}
	//연산자 우선순위 확인용
	private static char ckOper(Character peek) {
		if( peek == '(') {
			return 0;
		}
		else if ( peek == '+' || peek == '-') {
			return 1;
		}
		return 2;
	}
}