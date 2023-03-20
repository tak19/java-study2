package greedy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class test  {
	public static void main(String[] args) {
		int num = 1;
		
		try {
			System.out.println("try문장 실행");
			int i = 1 / num;
			System.out.println("예외 다음문장");
		}catch (Exception e) {
			System.out.println("예외잡음");
		} 
		finally {
			System.out.println("파이널문 실행");
		}
		System.out.println("예외 나옴");
	}
}   