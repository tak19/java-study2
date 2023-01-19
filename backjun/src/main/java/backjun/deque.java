package backjun;

import java.util.ArrayDeque;
import java.util.Deque;


public class deque {

	public static void main(String[] args) {
		Deque<Integer> dq = new ArrayDeque<>();
		
		dq.add(1); //앞쪽 입구로
		dq.addFirst(2); //add와 마찬가지로 앞쪽입구
		dq.addLast(3); //뒤쪽에 들어옴
		
		System.out.println(dq); //출력 : [2,1,3]
		
		dq.remove(); //앞쪽 지워짐
		System.out.println(dq); //출력: [1,3]
		dq.removeFirst(); //remove와 마찬가지로 앞쪽 지워짐
		System.out.println(dq); //[3]
		dq.addFirst(4);
		dq.addLast(5);
		System.out.println(dq); //출력: [4,3,5]
		
		System.out.println(dq.peek());
		System.out.println(dq.peekFirst());
		System.out.println(dq.peekLast());
		System.out.println(dq.contains(3));
		
		
	}

}
