package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class datastructure_18 {
	static int[] arr;
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Stack<Integer> stack = new Stack<>();
		n = Integer.parseInt(br.readLine());
		
		int result = 0;
		for(int i = 0 ; i < n ; i++) {
			stack.add( Integer.parseInt(br.readLine()) );
			if( stack.peek() == 0 ) {
				stack.pop();
				result -= stack.pop();
			}else {
				result += stack.peek();
			}
		}
		System.out.println(result);
	}
}