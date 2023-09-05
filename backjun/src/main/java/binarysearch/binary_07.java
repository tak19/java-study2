package binarysearch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class binary_07 {
	static StringBuilder sb = new StringBuilder();
	static int N,M;
	//static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		//전체 판 수와 이긴수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//결과값
		long result = -1;
		//이전 승률
		long pre = (long) Math.floor((double)M* 100 / (double)N);

		long min = 1;
		long max = 1000000000;

		//이분 탐색
		while( min <= max ) {
			long mid = (min + max) / 2;
			//바뀐 승률
			long per = (long)Math.floor((double)( M + mid ) * 100 / (double)( N + mid));
			if( pre < per ) {
				result = mid;
				max = mid-1;
			}else {
				min = mid+1;
			}
		}
		System.out.println( result );
		
	}
}