package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		//nC(2/n) 구함
		selectOne = new int[N];
		selectTwo = new int[N];
		combi(1,0);
		
		System.out.println(result);
		
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
