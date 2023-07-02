package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class datastructure_21 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Stack<Character> stack = null;
		int result = 0;
		for(int i = 0 ; i < N ; i++) {
			stack = new Stack<>();
			boolean ck = true;
			char[] arr = br.readLine().toCharArray();
			
			for(int j = 0 ; j < arr.length ; j++) {
				//원소 비어있따면 넣어야지!
				if( stack.isEmpty() ) {
					stack.add(arr[j]);
				}else {
					//원소가 차있따면
					if( stack.peek() == arr[j] ) {
						stack.pop();
					}else {
						stack.add(arr[j]);
					}
					
					
				}
			}
			if( !stack.isEmpty() ) {
				continue;
			}
			result++;

		}
		System.out.println(result);
		
	}
}