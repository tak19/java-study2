package combination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class combination_04 {
	private static StringBuilder sb = new StringBuilder();
	static int[] arr;

	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		arr = new int[n];
		//순열 저장함
		for(int i = 0 ; i < n ; i++) {
			arr[i] = i+1;
		}
		//문제 입력 받아욧
		st = new StringTokenizer(br.readLine());
		int what = Integer.parseInt(st.nextToken());

		int cnt = 0;
		int[] t = new int[n];

		if( what == 1) { //순서 물어보나?
			cnt = Integer.parseInt(st.nextToken());
		}else {// 순열 차례 물어보나?
			for(int i = 0 ; i < n ; i++) {
				t[i] = Integer.parseInt(st.nextToken());
			}
		}

		Arrays.sort(arr);
		int stop = 1;
		if(what == 1) {
			do {
				if( stop == cnt ) {//원하는 순서에 도착하믄
					for(int i = 0 ; i < n ; i++) {
						sb.append(arr[i] + " ");
					}
					break;
				}

				stop++;
			} while (npcom(arr));
		}else {
			
			do {
				if(Arrays.equals(arr, t)) {
					sb.append(stop);
					break;
				}
				stop++;
			} while (npcom(arr));
		}

		System.out.println(sb);

	}



	private static boolean npcom(int[] input) {
		int n = input.length;

		int i = n -1;
		while(i > 0 && input[i-1] >= input[i]) {
			i--;
		}
		if( i == 0 ) {
			return false;
		}

		int j = n -1;
		while( input[i-1] >= input[j] ) {
			j--;
		}
		swap(input,i-1,j);

		int k = n -1;
		while( i <= k ) {
			swap(input, i++, k--);
		}

		return true;
	}

	private static void swap(int[] input, int i, int j) {
		int tem = input[i];
		input[i] = input[j];
		input[j] = tem;
	}

}
