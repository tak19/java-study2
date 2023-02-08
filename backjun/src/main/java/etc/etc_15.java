package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_15 {
	static int n;
	static int m;
	static int result;
	static int[] arr;
	static int[] list;
	static int goal;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); //N장의 카드
		m = Integer.parseInt(st.nextToken()); //목표 숫자
		
		arr = new int[n];
		list= new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		result = -1;
		//3장 뽑는걸로 고정
		combi(0,0);
		System.out.println(result);
	}
	private static void combi(int cnt, int start) {
		int sum = 0;
		if( cnt == 3) {
			for(int i = 0 ; i < 3 ; i++) {
				sum += list[i];
			}
			if( sum <= m) {
				result = Math.max(result, sum);
			}
			return;
		}
		
		for(int i = start ; i < n ; i++) {
			list[cnt] = arr[i];
			combi(cnt+1, i+1);
		}
		
		
	}
}
