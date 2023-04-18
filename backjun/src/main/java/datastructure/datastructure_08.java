package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;


public class datastructure_08 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s= br.readLine();

		int result = 0;
		Stack<Character> stack = new Stack<>();
		//길이만큼 반복
		for(int i = 0 ; i < s.length(); i++) {
			char c = s.charAt(i);
			//닫는 괄호라면
			if( c == ')' ) {
				//레이저를 만남
				if( s.charAt(i-1) == '(' ) {
					stack.pop();
					result += stack.size();
				}else {
					//그냥 절단면임
					result++;
					stack.pop();
				}
			}else {
				//여는 괄호는 스택에 일단 넣음
				stack.add('(');

			}
		}
		System.out.println(result);

	}
}