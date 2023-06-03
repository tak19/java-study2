package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class datastructure_13 {
	static boolean[] select;
	static int cnt,visit,total;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		//TC실행
		for(int test = 1 ; test <= T ; test++) {
			char[] arr = br.readLine().toCharArray();
			Stack s = new Stack<Character>();
			String result = "YES";
			
			//괄호 판단
			for(int i = 0 ; i < arr.length; i++) {
				if( arr[i] == '(' ) {
					s.add('(');
				}else {
					//닫는 괄호가 나왔는데 여는 괄호가 없다면 조건 만족 x
					if( !s.isEmpty() ) {
						s.pop();
					}else {
						result = "NO";
						break;
					}
				}
			}
			//마지막에 짝지 맞게 나왔는지 확인
			if( !s.isEmpty() ) {
				result = "NO";
			}
			sb.append(result).append("\n");
			
		}
		System.out.println(sb);
	}
	
}