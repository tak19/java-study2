package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class etc_02 {
	static int N,totalCnt;
	static int[] arr,input,comOut;
	static int[][] comCnt;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = 4;
		visit = new boolean[4];
		arr = new int[4];
		comCnt = new int[5][5];

		//pec(0);
		//dispec(0);
		com(0,0);
		//discom(0,0);
		//System.out.println(cntCom(4,2));
		
//		input = new int[4];
//		for (int i = 0; i < 4; i++) {
//			input[i] = i;
//		}
//		
//		//NP 순열
//		do {
//			totalCnt++;
//			System.out.println(Arrays.toString(input));
//		} while (Np(input));
		

		//NP 조합
//		comOut = new int[5];
//		int last = 0;
//		while( last < 3 ) {
//			comOut[5-last-1] = 1;
//			last++;
//		}
//		do {
//			totalCnt++;
//			for(int i = 0 ; i < 5 ; i++) {
//				if( comOut[i] == 1 ) {
//					System.out.print(i + " ");
//				}
//			}
//			System.out.println();
//		} while (Np(comOut));
		
		System.out.println(totalCnt);

	}
	private static boolean Np(int[] input) {
		int n = input.length;
		int i = n -1;
		while( i > 0 && input[i] <= input[i-1] ) {
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
		while( i < k ) {
			swap(input, i++, k--);
		}
		return true;
	}
	//자리 교환
	private static void swap(int[] arr, int i, int j) {
		int tem = arr[i];
		arr[i] = arr[j];
		arr[j] = tem;
	}
	//조합 개수 -- N개중 R개 뽑음
	private static int cntCom(int N, int R) {
		
		if( comCnt[N][R] != 0) {
			return comCnt[N][R];
		}
		
		if( N == R || R == 0) {
			return comCnt[N][R] = 1;
		}
		
		return comCnt[N][R] = ( cntCom(N-1, R-1) + cntCom(N-1, R) );
		
	}
	//중복조합
	private static void discom(int cnt, int start) {
		if( cnt == 4) {
			System.out.println(Arrays.toString(arr));
			totalCnt++;
			return;
		}
		
		for(int i = start ; i < 4 ; i++) {
			
			arr[cnt] = i;
			discom(cnt+1,i);
			
		}
	}
	//조합
	private static void com(int cnt, int start) {
		if( cnt == 4) {
			System.out.println(Arrays.toString(arr));
			totalCnt++;
			return;
		}
		
		for(int i = start ; i < 4 ; i++) {
			
			arr[cnt] = i;
			com(cnt+1,i+1);
			
		}
		
	}

	//중복순열
	private static void dispec(int cnt) {
		if( cnt == 4) {
			System.out.println(Arrays.toString(arr));
			totalCnt++;
			return;
		}

		for(int i = 0 ; i < 4 ; i++) {
			arr[cnt] = i;
			dispec(cnt+1);
		}
	}
	//순열
	private static void pec(int cnt) {
		if( cnt == 4) {
			System.out.println(Arrays.toString(arr));
			totalCnt++;
			return;
		}

		for(int i = 0 ; i < 4 ; i++) {
			if( visit[i] ) {
				continue;
			}
			arr[cnt] = i;
			visit[i] = true;
			pec(cnt+1);
			visit[i] = false;
			
		}
	}
}
