package chapter04;

public class WrapperClasstest02 {
	public static void main(String[] args) {
		String s = "123456";
		int i = Integer.parseInt(s);
		
		//cf1 반대로~
		String s1 = String.valueOf(i);
		
		//cf2 반대로~
		String s2 = "" + i;
		
		System.out.println(s + " " + s1 + " " + s2);
		
		int a = Character.getNumericValue('A');
		System.out.println(a);
		char c = 'A';
		System.out.println((int)c);
		
		//2진수
		System.out.println(Integer.toBinaryString(15));
		//8진수
		System.out.println(Integer.toOctalString(15));
		//16진수
		System.out.println(Integer.toHexString(15));

		
		
		
		
		
		
		
	}
}
