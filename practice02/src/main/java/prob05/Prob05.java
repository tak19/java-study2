package prob05;

import java.util.Random;
import java.util.Scanner;

public class Prob05 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in );

		while( true ) {
			
			/* 게임 작성 */

			// 정답 램덤하게 만들기
			Random random = new Random();
			int correctNumber = random.nextInt( 100 ) + 1;
			System.out.println("수를 결정하셨습니다. 맞추어 보세요");
			System.out.println("correctNumber: " + correctNumber);
			
			int min  = 1;
			int max = 100;
			int cnt=1;
			String answer1 = "";
			//correctNumber 랜덤값
			while(true) {
				
				System.out.println(min + "-" + max);
				System.out.print(cnt + ">>");
				int answer = scanner.nextInt();
				
				if( answer == correctNumber) {
					min  = 1;
					max = 100;
					System.out.println( "맞았습니다" );
					System.out.print( "다시 하겠습니까(y/n)>>" );
					answer1 = scanner.next();
					break;
					
				}
				else if (answer > correctNumber) {
					System.out.println( "더 낮게" );
					max = answer;
				}else if (answer < correctNumber) {
					System.out.println( "더 눂게" );
					min = answer;
				}
				
			}
			
			if( "y".equals( answer1 ) == false ) {
				break;
			}

			
		}
		
		
	}

}