package greedy;

import java.util.Scanner;

public class greedy9 {

	public static void main(String[] args) {
		//1부터 더하는데 더할 수보다 남은 수가 많으면 +1 임
				Scanner sc =new Scanner(System.in);
				long n = sc.nextLong();
				int i = 1;
				int sum = 0;
				
				//주어진 수를 1부터 시작해서 더하기 시작함 -- 더한 수는 전체수에서 빼준다.
				while( n >= i ) {
					sum += i;
					n -= i ;
					i++;
				}
				//더한 수가 남은수(n)보다 크다면 회수를 1회 차감 아니면 그냥 출
				if( n - i < 0) {
					System.out.println(--i);
				}else {
					System.out.println(i);
				}
				
			}
		}