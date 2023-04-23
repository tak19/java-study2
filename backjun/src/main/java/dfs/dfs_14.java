package dfs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class dfs_14 {
	static int N,M,result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//수 입력받음
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		result = Integer.MAX_VALUE;
		search(1,M);
		result =  result == Integer.MAX_VALUE ? -1 : result;
		System.out.println(result);
	}
	//탐색 시작
	private static void search(int cnt, int now) {
		//목적 값보다 낮아지면 탐색 종료
		if( now < N ) {
			return;
		}
		//목적 값을 찾았다면
		if( now == N ) {
			result = Math.min(result, cnt);
			return;
		}
		//끝자리가 1로 끝난다면..
		if( now % 10 == 1 ) {
			search(cnt+1, now/10);
		}
		//2로 나눠 떨어지는 경우만 2로 나누어줌
		//원래 정수에서 2를 곱하기 때문에 소수점이 나올 수 없음
		if( now % 2 == 0 ) {
			search(cnt+1, now/2);
		}
		
		
		
	}
}