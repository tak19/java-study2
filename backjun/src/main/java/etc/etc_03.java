package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class etc_03 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		String[] s = br.readLine().split(" ");
		
		int n = Integer.parseInt(info[0]);
		int sub = Integer.parseInt(info[1]);
		long total=0;
		long[] arr = new long[n];
		long[] cnt = new long[sub];
		
		arr[0] = Integer.parseInt(s[0]);
		for(int i = 1 ; i < n ; i ++) {
			arr[i] = arr[i-1] + Integer.parseInt(s[i]);
		}
		
		
		for(int i = 0 ; i < n ; i ++) {
			int re = (int) (arr[i] % sub);
			if(re == 0) {
				total++;
			}
			//나머지 값을 인덱스로 사용하여 갯수 파악
			cnt[re]++;
		}

		for(int i = 0 ; i < sub ; i ++) {
			//나머지 갯수가 1개 이상이면.. 쌍으로 뽑아서 조합 계산이 가능해야하기 때문
			if (cnt[i] > 1) {
				total += (cnt[i] * (cnt[i]-1)) / 2;
			}
				
		}
		
		System.out.println(total);
	}

}
