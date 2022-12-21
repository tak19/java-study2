package chapter03;

public class SwapTest02 {
	//static int a = 10;
	//static int b = 20;
	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		System.out.println("a: " + a + " ,b: " +b);
		
		
		swap(a,b);
		
		System.out.println("a: " + a + " ,b: " +b);

	}
	//Call by value
	public static void swap(int m, int n) {
		int tem = m;
		m = n;
		n = tem;
	}

}
