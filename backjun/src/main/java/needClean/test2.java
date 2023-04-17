package needClean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;


public class test2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[31];
		for(int i = 0 ; i < 28 ; i++) {
			int tem = Integer.parseInt(br.readLine());
			arr[tem]++;
		}
		for(int i = 1 ; i <= 30 ; i++) {
			if( arr[i] == 0 ) {
				System.out.println(i);
			}
		}
	}
}