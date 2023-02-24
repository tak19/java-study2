package bruteforce;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bruteforce_05 {
	private static StringBuilder sb = new StringBuilder();
	static int n,min,max;
	static int[] arr,oper,result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n  = Integer.parseInt(br.readLine()); //수의 개수
		//수열 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//연산자 입력받기
		st = new StringTokenizer(br.readLine());
		oper = new int[4];
		for(int i = 0 ; i< 4 ; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}
		result = new int[n-1];
		
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		//n-1 개만큼 뽑아야함
		operPec(0);
		
		sb.append(max).append("\n").append(min);
		System.out.println(sb);
	}
	//연산자의 개수를 보고 사용가능여부를 판단할꺼
	private static void operPec(int cnt) {
		if( cnt == n-1) {
			cal();
			return;
		}
		//연산자 순열 유도부
		for(int i = 0 ; i < 4 ; i++) {
			//사용 가능 연산자가 남아있다면
			if(oper[i] > 0) {
				result[cnt] = i; //i순서로 연산자 구분
				oper[i]--; //사용 연산자 수 감소
				operPec(cnt+1); //다음 순서 연산자 계산한다
				oper[i]++; //사용 연산자 수 원복 - 다른경우 위해
			}
		}
		
	}
	//만들어진 부호 순열로 계산함
	private static void cal() {
		int num = arr[0];
		// '+' -> '-' -> '*' -> '/' 순 
		for(int i = 1 ; i < n ; i++) {
			switch (result[i-1]) {
			case 0: {
				num += arr[i];
				break;
			}
			case 1: {
				num -= arr[i];
				break;
			}
			case 2: {
				num  *= arr[i];
				break;
			}
			case 3: {
				num  /= arr[i];
				break;
			}

			}
		}
			
		max = Math.max(max, num);
		min = Math.min(min, num);

	}
}

