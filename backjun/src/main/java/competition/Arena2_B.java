package competition;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Arena2_B {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//StringTokenizer st = new StringTokenizer(br.readLine());
		String s = "";
		int num = 0;
		for(int i = 0 ; i < 3 ; i++) {
			s = br.readLine();
			if( s.equals("FizzBuzz") || s.equals("Fizz") || s.equals("Buzz") ) {
				continue;
			}
			num = Integer.parseInt(s);
			num += 3 - i;
		}
		if( num % 3 == 0 && num % 5 == 0 ) {
			System.out.println("FizzBuzz");
		}else if( num % 3 == 0 && num % 5 != 0 ) {
			System.out.println("Fizz");
		}else if( num % 3 != 0 && num % 5 == 0 ) {
			System.out.println("Buzz");
		}else {
			System.out.println(num);
		}
		
	}
}