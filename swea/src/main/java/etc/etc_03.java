package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class etc_03 {
	static int[] arr,oper,out; //숫자입력, 연산자 수, 연산자 사용
	static int len; //숫자 길이
	static int max,min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			//숫자 길이
			len = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine()); //부호 입력 받음
			//연산자 입력받음
			oper = new int[4];
			for(int i = 0 ; i < 4 ; i++) {
				oper[i] = Integer.parseInt(st.nextToken());
			}
			//숫자 입력받음
			st = new StringTokenizer(br.readLine()); 
			arr = new int[len];
			for(int i = 0 ; i < len ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			out = new int[len-1];
			makePer(0);

			sb.append(max - min).append("\n");

		}
		System.out.println(sb);

	}
	//뽑은 횟수와 배열
	private static void makePer(int cnt) {
		if( cnt == len -1) {
			int result = arr[0];
			//System.out.println(Arrays.toString(output));
			for(int i = 0 ; i < len -1 ; i++) {
				if( out[i] == 0 ) {
					result = result + arr[i+1]; 
				}else if( out[i] == 1 ) {
					result = result - arr[i+1];
				}else if(  out[i] == 2  ) {
					result = result * arr[i+1];
				}else if(  out[i] == 3  ) {
					result = result / arr[i+1];
				}
			}
			min = Math.min(min, result);
			max = Math.max(max, result);

			return;
		}
		for(int i = 0 ; i < 4 ; i++) {
			if(oper[i] > 0) { //사용안한 연산자가 있는지 확인
				oper[i]--; //해당 연산자 사용후 감소
				out[cnt] = i; //해당 자리에 사용 연산자 대입
				makePer(cnt+1);
				oper[i]++;
			}
		}

	}

}

