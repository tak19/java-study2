package simul;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class simul_19 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();
		
		int[] num = new int[10];
		for(int i = 0 ; i < arr.length ; i++) {
			int n = arr[i] - '0';
			num[n]++;
		}
		num[6] += num[9];
		if( num[6] % 2 != 0 ) {
			num[6]++;
		}
		num[6] /= 2;
		
		int result = 0;
		for(int i = 0 ; i <= 8 ; i++) {
			if( result < num[i] ) {
				result = num[i];
			}
		}
		System.out.println(result);
		
	}
}