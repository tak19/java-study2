package prob04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class Prob04 {

	public static void main(String[] args) {
		char[] c1 = reverse("Hello World");
		printCharArray(c1);

		char[] c2 = reverse("Java Programming!");
		printCharArray(c2);
	}

	public static char[] reverse(String str) {
		/* 코드를 완성합니다 */
		/*
		 //배열을 활용한 알고리즘 
		 char[] c = new char[str.length()]; 
		 for(int i = 1 ; i <= str.length() ; i++) {
		  	c[i-1] = str.charAt(str.length()-i); 
		  	}
		 */

		/*
		 //StringBuffer 이용한 역순출력 
		 StringBuffer sb = new StringBuffer(str); 
		 char[] c = sb.reverse().toString().toCharArray();
		 */

		
		
		// 값을 출력하기위한 ListIterator || get 출력
		List<Character> list = new ArrayList<>();
		char[] c = str.toCharArray();
		for (char rr : c)
			list.add(rr);
		// Collections를 사용 ArrayList 값 반전
		Collections.reverse(list);
		for(int i = 0 ;  i < list.size(); i++) {
			c[i] = list.get(i);
		}

		int num=0;
		ListIterator li = list.listIterator();
		while (li.hasNext()) {
			c[num] = (char) li.next();
			num++;
			// System.out.print(li.next());
		}
		
		return c;

			
	}

	public static void printCharArray(char[] array) {
		/* 코드를 완성합니다 */
		System.out.println(array);
	}
}