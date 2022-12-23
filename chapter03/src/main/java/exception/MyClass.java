package exception;

import java.io.IOException;

public class MyClass {
	public void danger() throws IOException, MyException  {
		System.out.println("some code1...");
		System.out.println("some code2...");
		
		if(true) {
			throw new MyException();
		}
		
		if(true) {
			throw new IOException();
		}
		
		System.out.println("some code3...");
		System.out.println("some code4...");
		
	}
}
