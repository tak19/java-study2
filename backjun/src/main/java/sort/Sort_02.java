package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sort_02 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		for(int i = 0; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		//배열 / 0 / 크기 / 찾는인덱스
		quickSort(arr,0,N-1,K-1);
		System.out.println(arr[K-1]);
	}

	private static void quickSort(int[] arr, int S, int E, int K) {
		
		if ( S < E ) {
			int pivot = partition(arr,S,E);
			if ( pivot == K ) { //원하는 수가 피봇이라면
				return;
			}else if( K < pivot ) { // K가 피봇보다 작으면 왼쪽만 정렬하면됨
				quickSort(arr, S, pivot - 1, K);
			}else {
				quickSort(arr, pivot + 1, E, K);
			}
		}
	}

	private static int partition(int[] arr, int S, int E) {
		if( S + 1 == E) {
			if( arr[S] > arr[E] ) swuap(arr,S,E);
			return E;
		}
		int M = (S+E) / 2;
		swuap(arr, S, E);
		int pivot = arr[S];
		int i = S + 1, j = E;
		while ( i <= j ) {
			while(pivot < arr[j] && j > 0 ) {
				j--;
			}
			while( pivot > arr[i] && i < arr.length - 1 ) {
				i++;
			}
			if( i <= j ) {
				swuap(arr, i++, j--);
			}
		}
		arr[S] = arr[j];
		arr[j] = pivot;
		return j;
	}

	private static void swuap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}





