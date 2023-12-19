package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class samsung_01 {
	static int N,result;
	static int[][] arr;
	static int[] selectOne,selectTwo;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];
		
		//배열 입력받기
		for(int i = 1 ; i <= N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j <= N ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = Integer.MAX_VALUE;
		
		selectOne = new int[N];
		selectTwo = new int[N];
		
		// 1.일반적인 조합 계산
		//nC(2/n) 구함
		//combi(1,0);
		
		
		// 2.NP를 이용한 빠른 계산
		
		//초기 배열 만들기
		int[] input = new int[N];
		int last = 0;
		while(last < N/2) {
			input[N-last-1] = 1;
			last++;
		}
		
		//NP 실행
		do {
			int index = 0;
			for(int i = 0 ; i < N ; i++) {
				if( input[i] == 1 ) {
					selectOne[index++] = i+1;
				}
			}
			anotherArr();
			//System.out.println(Arrays.toString(selectOne));
			int a = calPoint(selectOne);
			int b = calPoint(selectTwo);
			result = Math.min(result, Math.abs(a-b));
			
		}while( np(input) );
		
		System.out.println(result);
		
	}
	//NP
	private static boolean np(int[] input) {
		
		int i = input.length-1;
		while( i > 0 && input[i] <= input[i-1] ) {
			i--;
		}
		if(i==0) {
			return false;
		}
		
		int j = input.length-1;
		while( input[i-1] >= input[j] ) {
			j--;
		}
		swap(input,i-1,j);
		int k = input.length-1;
		while(i < k) {
			swap(input,i++,k--);
		}
		return true;
	}
	//자리 교환
	private static void swap(int[] input, int i, int j) {
		int tem = input[i];
		input[i] = input[j];
		input[j] = tem;
	}
	//조합 계산
	private static void combi(int start, int cnt) {
		//다 뽑았다면
		if( cnt == (N/2) ) {
			anotherArr();
			int a = calPoint(selectOne);
			int b = calPoint(selectTwo);
			//System.out.println("뭐야 이거 : " + Math.abs(a-b));
			result = Math.min(result, Math.abs(a-b));
			return;
		}
		for(int i = start ; i <= N ; i++) {
			//뽑았다면
			selectOne[cnt] = i;
			combi(i+1,cnt+1);
		}
	}
	//점수계산 Method
	private static int calPoint(int[] selectOne2) {
		int sum = 0;
		for(int i = 0 ; i < N/2 ; i++) {
			for(int j = 0 ; j < N/2 ; j++) {
				sum += arr[selectOne2[i]][selectOne2[j]];
			}
		}
		//System.out.println("sum: " + sum );
		return sum;
	}
	//다른 쪽 팀 구하기
	private static void anotherArr() {
		int nowIndex = 0;
		for(int i = 1 ; i <= N ; i++) {
			if( ckNum(i) ) {
				selectTwo[nowIndex++] = i;
			}
		}
	}

	//숫자가 있는지 체크
	private static boolean ckNum(int num) {
		for(int i = 0 ; i < N ; i++) {
			if( selectOne[i] == num ) {
				return false;
			}
		}
		return true;
	}
}
