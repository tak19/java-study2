package backjun;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class greedy3 {

	public static void main(String[] args) {
		//백준 10610 - 30
		//30 배수 조건 ->  0이 있는지 확인 + 3의 배수인지 확인 
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		char[] c = s.toCharArray();
		sc.close();
		
		//30의 배수인지 확인하기 위한 변수 -> 30배수라고 생각하고 아니면 1로 변경
		int ck = 0;
		// 모든자리수의 합을 저장
		int total = 0;
		
		
		//수에 0 이 없으면 조건을 만족하지 않음 -> indexof함수 이용 0이 있다면 인덱스 값 반환하겠지 아니면 -1
		int oneindex = s.indexOf("0");

		// char[] 에 저장한 수를 하나씩 보면서 더함
		for (int i = 0; i < c.length; i++) {
			total += c[i];
		}
		// 모든 자리 수의 합이 3으로 나눠지지 않으면 3의 배수가 아님 -> 따라서 3의 배수가 아니므로 index값을 -1로 수정
		if(total % 3 != 0) {
			oneindex = -1;
		}
		
		if( oneindex == -1) {
			System.out.println(-1);
		}else {
			//이제 최대값을 찾을꺼임 -- 각 돌면서 합이 가장 큰것만
			//버블소트 시간초과
//			for (int i = 0; i < c.length; i++) {
//				for(int j = 1 ; j < c.length ; j++) {
//					//앞에 수가 더 크다면... 앞으로 와야함
//					if(c[j-1] < c[j]) {
//						char max = c[j];
//						c[j] = c[j-1]; 
//						c[j-1] = max;
//					} else {
//						continue;
//					}
//				}
//			}
			Arrays.sort(c);
			for(int i = c.length -1; i >= 0 ; i --) {
				System.out.print(c[i]);
			}
			
			
			

		}

	}
}
