package prime;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class prime_03 {
	public static void main(String[] args) throws Exception{
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put(1, "사람");
//		map.put('2', "야구");
		
		TreeSet<Integer> s = new TreeSet<>();
		
		s.add(1);
		s.add(4);
		s.add(2);
		s.add(1);
		
		for (Iterator iterator = s.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());
			
		}
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int n = Integer.parseInt(br.readLine());
//		// 계산해보니 1000000 이상인 최소 팰린드롬 수는 1003001 이다
//		boolean[] isSelect = new boolean[1003002];
//		for(int i = 2 ; i < 1003002; i++ ) {
//			if( !isSelect[i] ) {
//				//소수인데 n보다 크거나 같은지
//				if( n <= i ) {
//					//그렇다면 펠린드롬 검사함
//					if( cal(i) ) {
//						System.out.println(i);
//						break;
//					}
//				}
//				//System.out.print(i + " ");
//				for(int j = i+i ; j < 1003002; j += i) {
//					isSelect[j] = true;
//				}
//			}
//		}
		
		
		
	}
	//팰린드롬인지 확인
	private static boolean cal(int i) {
		Deque<Integer> dq = new ArrayDeque<>();
		while( i != 0 ) {
			//10으로 나눈 나머지로 자리수를 저장함
			int a = i % 10;
			dq.offer(a);
			i /= 10;
		}
		//자리수 저장함
		int size = dq.size();
		//사이즈의 반만큼 실행 -> 짝수면 다 실행 홀수도 상관없음 중앙값으 대칭이기때문
		for(int k = 0 ; k < size / 2 ; k++) {
			int first = dq.pollFirst();
			int last = dq.pollLast();
			//두 원소가 같지 않다면 false 반환
			if( first != last) {
				return false;
			}
		}
		return true;
	}
}





