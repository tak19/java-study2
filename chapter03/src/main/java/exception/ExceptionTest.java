package exception;

public class ExceptionTest {

	public static void main(String[] args) {
		int a = 10;
		int b = 10 - a;
		
		System.out.println("Some code...1");
		try {
			System.out.println("Some code...2");
			System.out.println("Some code...3");
			
			int result = (1+2+3) / b ;
			
			System.out.println("Some code...4");
			System.out.println("Some code...5");
			
		}catch(ArithmeticException ex) {
			//예외 처리
			//1. 로깅
			System.out.println("error: " + ex);
			
			//2. 사과
			System.out.println("정말 미안");
			//3. 정상종료
			//System.exit(0);
			return;
			
		}finally {
			//catch에서 return 만나도 실행됨  |||| System.exit 는 종료시키때문에 finally 실행 x
			System.out.println("자원 정리 예: file close....");
		}
		System.out.println("Some code...6");
		System.out.println("Some code...7");
		

	}

}
