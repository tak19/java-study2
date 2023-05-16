package datastructure;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Format;
import java.util.Stack;
//9,876,543,210
public class datastructure_10 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Double> stack = new Stack<>();
		String s = br.readLine();

		//각 문자에 대응하는 값 입력 받기
		double[] arr =new double[26];
		for(int i = 0 ; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int result = 0;
		//후위 표기 계산함
		for(int i = 0 ; i < s.length(); i++) {
			char c = s.charAt(i);
			//숫자라면
			if( 'A' <= c && c <= 'Z' ) {
				stack.push(arr[c-'A']);
			}else {
				double b = stack.pop();
				double a = stack.pop();
				double tem = 0;
				switch (c) {
				case '+':
					tem = a + b;
					break;
				case '-':
					tem = a - b;
					break;
				case '/':
					tem = a / b;
					break;
				case '*':
					tem = a * b;
					break;
				}
				stack.push(tem);
			}
		}
		
		System.out.printf("%.2f",stack.peek());

	}
}