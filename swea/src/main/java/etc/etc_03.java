package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class etc_03 {
	static int[] arr;
	static int len,size;
	static Character[] c;
	static char[] output;
	static boolean[] isSelect;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test <= 1 ; test++) {
			sb.append("#").append(test).append(" ");
			//숫자 길이
			len = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine()); //부호 입력 받음
			
			List<Character> list = new ArrayList<>();
			int plus = Integer.parseInt(st.nextToken());
			int sub = Integer.parseInt(st.nextToken());
			int mul = Integer.parseInt(st.nextToken());
			int div = Integer.parseInt(st.nextToken());
			//+
			for(int i = 0; i < plus; i++) {
				list.add('+');
			}
			//-
			for(int i = 0; i < sub; i++) {
				list.add('-');
			}
			//*
			for(int i = 0; i < mul ; i++) {
				list.add('*');
			}
			///
			for(int i = 0; i < div ; i++) {
				list.add('/');
			}
			
			c = list.toArray(new Character[0]);
			size = c.length;
			//숫자 입력받음
			st = new StringTokenizer(br.readLine()); 
			arr = new int[len];
			for(int i = 0 ; i < len ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			output = new char[size];
			isSelect = new boolean[size];
			pumul(0);
			
			
		}
		
	}

	private static void pumul(int cnt) {
		if( cnt == size ) {
			System.out.println(Arrays.toString(output));
			return;
		}
		for(int i = 0 ; i < size ; i++) {
			if( isSelect[i] ) {
				continue;
			}
			output[cnt] = c[i]; //i번쨰에 해당하는 값을 집어넣음
			isSelect[i] = true; //뽑은 숫자 체크
			pumul(cnt + 1); //다음 숫자 뽑으러 가기
			isSelect[i] = false; //리턴하고 돌아 왔을 때 뽑지 않은 상태로 되돌림	
		}
		
		
		
	}
}

