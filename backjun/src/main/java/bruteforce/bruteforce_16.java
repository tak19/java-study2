package bruteforce;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class bruteforce_16 {
	static int N;
	static long max,min;
	static char[] arr;
	static boolean[] select;
	static String minResult,maxResult;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 부등호 갯수
		arr = new char[N+1]; // 부등호 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//부등호 입력받기
		for(int i = 1 ; i <= N ; i++) {
			arr[i] =  st.nextToken().charAt(0);
		}
		//System.out.println(Arrays.toString(arr));
		select = new boolean[10];
		min = Long.MAX_VALUE;
		max = Long.MIN_VALUE;
		bruteforce(0,"");
		System.out.println(maxResult + "\n" + minResult);
	}
	//탐색한다
	private static void bruteforce(int cnt, String nums) {
		//다 뽑았다면
		if( cnt > N ) {
			long tem = Long.parseLong(nums);
			if( min > tem ) {
				minResult = nums;
				min = tem;
			}
			if( max < tem ) {
				maxResult = nums;
				max = tem;
			}
			
			return;
		}
		//모든 수 탐색
		for(int i = 0 ; i <= 9 ; i++) {
			//뽑은 수는 제외
			if( select[i] ) {
				continue;
			}
			//부등호에 따른 처리
			if( arr[cnt] == '>' ) {
				//전 숫자가 현재 수보다 크다면
				if( nums.charAt(cnt-1) -'0' > i ) {
					select[i] = true;
					bruteforce(cnt+1, nums + i);
					select[i] = false;
				}else{
					continue;
				}
				
			}else if( arr[cnt] == '<' ) {
				
				//전 숫자가 현재 수보다 작다면
				if( nums.charAt(cnt-1) -'0' < i ) {
					select[i] = true;
					bruteforce(cnt+1, nums + i);
					select[i] = false;
				}else{
					continue;
				}
				
				
			}else {
				//첫번째 자리
				select[i] = true;
				bruteforce(cnt+1, nums + i);
				select[i] = false;
			}
		}
		
		
	}
}