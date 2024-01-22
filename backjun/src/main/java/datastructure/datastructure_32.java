package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class datastructure_32 {
	//static StringBuilder result = new StringBuilder();
	static int student, currentOrder;
	static Queue<Integer> waitLine;
	static Stack<Integer> tempLine;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// 전체학생수
		student = Integer.parseInt(br.readLine());
		// 대기라인과 임시라인 선언
		waitLine = new ArrayDeque<Integer>();
		tempLine = new Stack<Integer>();

		// 순서 입력받음
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= student; i++) {
			waitLine.offer(Integer.parseInt(st.nextToken()));
		}
		String result = "Nice";
		currentOrder = 1; // 현재 순서인덱스를 요놈에 저장
		// 잘 드가지는지 확인
		while (currentOrder <= student) {
			if (topIndexSearchQ(waitLine)) { // 현재 순서가 가장 위에 있다면
				waitLine.poll();
				++currentOrder;
			} else if (topIndexSearchS(tempLine)) {
				tempLine.pop();
				++currentOrder;
			} else {
				if (!waitLine.isEmpty()) {
					// 없다면 temp에 다 넣어
					tempLine.add(waitLine.poll());
				} else {
					result = "Sad";
					break;
				}
			}
		}
		if( waitLine.isEmpty() && tempLine.isEmpty() ) {
			result = "Nice";
		}
		System.out.println(result);

	}

	// 현재 큐에 가장 위에있는 원소 반환
	private static boolean topIndexSearchQ(Queue<Integer> fucQueue) {
		if (!fucQueue.isEmpty()) {
			if (fucQueue.peek() == currentOrder)
				return true;
		}
		return false;
	}

	// 현재 스택에 가장 위에있는 원소 반환
	private static boolean topIndexSearchS(Stack<Integer> fucStack) {
		if (!fucStack.isEmpty()) {
			if (fucStack.peek() == currentOrder)
				return true;
		}
		return false;
	}

}