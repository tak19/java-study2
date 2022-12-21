package chapter03;

public class SwapTest01 {

	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		
		System.out.println("a: " + a + " ,b: " +b);
		
		
		int tem = a;
		a = b;
		b = tem;
		
		System.out.println("a: " + a + " ,b: " +b);

	}

}
