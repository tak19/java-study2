package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class datastructure_29 {
	static StringBuilder sb = new StringBuilder();
	static int N,M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		//전체 수와 뽑을 숫자수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//데크에 숫자 채우기
		Deque<Integer> dq = new ArrayDeque<>();
		for(int i =  1 ; i <= N ; i++) {
			dq.add(i);
		}
		int result = 0;
		st = new StringTokenizer(br.readLine());
		//숫자 입력받음
		for(int i = 0 ; i < M ; i++) {
			//찾을 위치의 수(처음 위치임!!)
			int searchNum = Integer.parseInt(st.nextToken());
			if( dq.peekFirst() == searchNum ) {
				//일치하면 바로 제거
				dq.removeFirst();
			}else {
				//왼쪽,오른쪽 최소를 구함
				int left = LCountDq(new ArrayDeque<Integer>(dq),searchNum);
				int right = RCountDq(new ArrayDeque<Integer>(dq),searchNum);
				//최소횟수를 더해준다.
				result += Math.min(left, right);
				//데크 변경
				if( left < right ) {
					LMoveDeque(dq,left);
				}else {
					RMoveDeque(dq,right);
				}
				
				//해당 원소 제거
				dq.remove(searchNum);
			}
		}
		System.out.println(result);
		
	}
	private static void RMoveDeque(Deque<Integer> dq, int right) {
		for(int i = 0 ; i < right ; i++) {
			dq.addFirst(dq.pollLast());
		}
	}
	private static void LMoveDeque(Deque<Integer> dq, int left) {
		for(int i = 0 ; i < left ; i++) {
			dq.addLast(dq.pollFirst());
		}
	}
	private static int LCountDq(Deque<Integer> dq, int searchNum) {
		int cnt = 0;
		while( dq.peekFirst() != searchNum ) {
			dq.addLast(dq.pollFirst());
			cnt++;
		}
		return cnt;
	}
	private static int RCountDq(Deque<Integer> dq, int searchNum) {
		int cnt = 1;
		while( dq.peekLast() != searchNum ) {
			dq.addFirst(dq.pollLast());
			cnt++;
		}
		return cnt;
	}
}
