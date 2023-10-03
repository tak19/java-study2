package competition;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BSCup_C {
	static StringBuilder sb = new StringBuilder();
	static int N,M;
	static int[] arrA,arrB;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int Test = Integer.parseInt(br.readLine());
		//TC만큼 반복
		for(int T = 0 ; T < Test ; T++ ) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			//A,B 만큼의 배열 생성
			arrA = new int[N];
			arrB = new int[M];
			
			//A배열 입력
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				arrA[i] = Integer.parseInt(st.nextToken());
			}
			//B배열 입력
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < M ; i++) {
				arrB[i] = Integer.parseInt(st.nextToken());
			}
			int sum = 0;
			//B배열 초기화
			Arrays.sort(arrB);
			for(int i = 0 ; i < N ; i++) {
				sum += binarysearch(arrA[i]);
			}
			
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}

	//이분 탐색 
	private static int binarysearch(int num) {
		int min = 0;
		int max = M-1;
		int result = 0;
		//이분 탐색 루프
		while( min <= max ) {
			//중간값
			int mid = (min + max) / 2;
			if( arrB[mid] < num ) {
				min = mid + 1;
				result = mid + 1;
			}else {
				max = mid -1;
			}
		}
		return result;
	}
}