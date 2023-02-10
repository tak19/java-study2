package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class d4_1218 {
private static StringBuilder sb = new StringBuilder();
//swea 괄호 짝짓기
public static void main(String[] args) throws Exception {
	/*
	 * 1. 입력파일 읽어 들이기
	 */
	System.setIn(new FileInputStream("res/input1.txt"));
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	/*
	 * 2. 입력파일 객체화
	 */
	int T = 10;
	
	
	for(int test_case = 1 ; test_case <= 10 ; test_case++) {
		sb.append("#" + test_case + " ");
		int len = Integer.parseInt(br.readLine());
		char[] c = br.readLine().toCharArray();
		

		//'()', '[]', '{}', '<>' 
		Stack<Character> s = new Stack<>();
		//검사시작
		for(int i = 0 ; i < c.length ; i++) {
			if( c[i] == '(' || c[i] == '[' || c[i] == '{' || c[i] == '<' ) {
				s.add(c[i]);
			}
			else if( c[i] == ')' || c[i] == ']' || c[i] == '}' || c[i] == '>' ){
				
				if( Math.abs(c[i] - s.pop()) > 3 ) {
					sb.append(0).append("\n");
					break;
				}

			}
		}
		if( s.isEmpty() ) {
			sb.append(1).append("\n");
		}
		
	}
	
	/*
	 * 3. 알고리즘 풀기
	 */
	
	/*
	 * 4. 정답출력
	 */
	System.out.println(sb);
	
}
}
