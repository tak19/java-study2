package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class etc_23 {
	static int sum,n;
	static int[] arr;
	static boolean[] isSelected;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); //수열크기
		sum = Integer.parseInt(st.nextToken()); //합
		
		arr = new int[n];
		isSelected = new boolean[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); //배열입력받음
		}
		result = 0;
		//정렬실행함
		Arrays.sort(arr);
		SubSet(0,0);
		
		System.out.println(result);
	}

	private static void SubSet(int cnt, int hap) {
		if( cnt == n ) {
			if( hap == sum) {
				boolean ck = false; //공집합 제외하기 위해
				for(int i = 0 ; i < n ; i++) {
					if( isSelected[i] ) {
						ck = true;
						break;
					}
				}
				if(ck) {
					result++;
				}	
			}	
			return;
		}
		//자기 포함
		isSelected[cnt] = true;
		SubSet(cnt+1, hap + arr[cnt]);
		isSelected[cnt] = false;
		//자신 미포함
		SubSet(cnt+1, hap);
		
	}
}
