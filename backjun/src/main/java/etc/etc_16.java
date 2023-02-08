package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_16 {
	static int[] arr;
	static int[] list;
	static int k;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if( k == 0 ) {
				break;
			}
			arr = new int[k];
			list = new int[6]; //로또 6자리
			for(int i = 0 ; i < k ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			combi(0,0);
			sb.append("\n");
			
			
		}
		System.out.println(sb);
		
	}
	private static void combi(int cnt, int start) {
		if ( cnt == 6 ) {
			for(int i = 0 ; i < 6; i ++) {
				sb.append(list[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for(int i = start ; i < k ; i++) {
			list[cnt] = arr[i];
			combi(cnt+1, i+1);
		}
		
	}
}
