package prob01;

public class Printer {

//	public void println(int i) {
//		System.out.println(i);
//		
//	}
//	public void println(boolean b) {
//		System.out.println(b);
//		
//	}
//	public void println(double d) {
//		System.out.println(d);
//		
//	}
//	public void println(String s) {
//		System.out.println(s);
//		
//	}
	
	public <T> void println(T... ts) {
		for(T t : ts) {
			System.out.println( t);
		}
		
		
	}
	
	//재너릭의 사용 제한을 위해 상속,슈퍼를 통해 기능 제한 가능
//	public <T extends Number> void println(T t) {
//		System.out.println( t);
//		
//	}
	
	//가변 파라매터 표시 ... 
	public int sum(Integer... nums) {
		
		int s = 0;
		for(Integer n :nums) {
			s += n;
		}
		return s;
		
	}
	
	
	

}
















