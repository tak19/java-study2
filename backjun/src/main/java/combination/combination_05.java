package combination;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class combination_05 {
	private static StringBuilder sb = new StringBuilder();
	static int[] arr,nan;
	static int sum;

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[9];
		nan = new int[7];
		for(int i = 0 ; i < 9 ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		combi(0,0);
		
		
		
		System.out.println(sb);


	}

	private static void combi(int cnt, int start) {
		if(cnt == 7) {
			if(sum == 100) {
				for(int t : nan) {
					sb.append(t).append("\n");
				}
			}
			return;
		}
		
		for(int i = start ; i < 9 ; i++) {
			nan[cnt] = arr[i];
			sum += arr[i];
			combi(cnt+1, i+1);
			sum -= arr[i];
		}
	}

}
