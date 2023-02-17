package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class d4_3234 {
	static int[] arr,ouput;
	static boolean[] isSelect;
	static int n,left,rigth,result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			n = Integer.parseInt(br.readLine());
			
			arr = new int[n]; //전체 배열
			ouput = new int[n]; //순열을 저장할 배열
			isSelect = new boolean[n];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n ; i++) {
				arr[i] = Integer.parseInt(st.nextToken()); 
			}
			result = 0;
			//순열계산함 -> 구슬 배치도
			//pec(0);
			
			//NP 사용
			Arrays.sort(arr);
			
			do {
				npsubSet(0,0,0);
				
			} while (npPec(arr));
			
			sb.append(result).append("\n");

		}
		System.out.println(sb);

	}
	private static boolean npPec(int[] input) {
		int n = input.length;
		
		int i = n -1;
		while( i > 0 && input[i-1] >= input[i] ) {
			i--;
		}
		if( i== 0) {
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
	
	private static void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}	
	
	private static void npsubSet(int cnt, int rigthSum, int leftSum) {
		if( rigthSum < leftSum) {
			return;
		}
		if(cnt == n) {
			if( rigthSum >= leftSum ) {
				result += 1;
			}
			return;
		}
		//자기를 포함한 경우 -> 오른쪽
		npsubSet(cnt+1, rigthSum + arr[cnt] , leftSum);
		
		//자기를 포함안한경우 -> 왼쪽으로 이동
		npsubSet(cnt+1, rigthSum, leftSum + arr[cnt]);
	
	}
	
	//NP - x
	private static void pec(int cnt) {
		if(cnt == n) {
			subSet(0,0,0); //부분집합 구함 --> 오,왼 배치 다르게함
			return;
		}
		
		for(int i = 0 ; i < n ; i++) {
			if( isSelect[i] ) { //뽑았다면 다른거
				continue;
			}
			isSelect[i] = true;
			ouput[cnt] = arr[i];
			pec(cnt+1);
			isSelect[i] = false; //뽑은거 다시 원상태로
			
		}
		
	}

	private static void subSet(int cnt, int rigthSum, int leftSum) {
		if( rigthSum < leftSum) {
			return;
		}
		if(cnt == n) {
			if( rigthSum >= leftSum ) {
				result += 1;
			}
			return;
		}
		//자기를 포함한 경우 -> 오른쪽
		subSet(cnt+1, rigthSum + ouput[cnt] , leftSum);
		
		//자기를 포함안한경우 -> 왼쪽으로 이동
		subSet(cnt+1, rigthSum, leftSum + ouput[cnt]);
	
	}
}
