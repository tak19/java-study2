package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class etc_14 {
	static int[] arr;
	static int[] nan;
	static boolean ck;
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		arr = new int[9];
		for(int i = 0 ; i < 9 ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		nan = new int[7];
		ck = false;
		combi(0,0);

		

	}
	//cnt 자리수 ,,, start 시작수
	private static void combi(int cnt, int start) {
		if( cnt == 7 ) {
			if(cal()) {
				for(int i = 0 ; i < 7 ; i++) {
					System.out.println(nan[i]);
				}
			}
			return;
		}

		for(int i = start ; i < 9 ; i++) {
			nan[cnt] =  arr[i];
			combi(cnt+1, i + 1);
		}


	}
	private static boolean cal() {
		int sum = 0;
		if( !ck ) {
			for(int i = 0 ; i < 7 ; i++) {
				sum += nan[i];
			}
			if( sum == 100) {
				ck = true;
				return true;
			}
		}
		
		return false;
	}
}
