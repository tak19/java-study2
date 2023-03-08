package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class greedy13 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); //수열의 길이
		
		//오름 차순 정렬함 - 양수
		PriorityQueue<Integer> plusPQ = new PriorityQueue<>((o1,o2) -> {
			return o2 - o1;
		});
		
		//오름 차순 정렬함 - 양수
		PriorityQueue<Integer> minPQ = new PriorityQueue<>((o1,o2) -> {
			return o1 - o2;
		});
		
		//수열 집어 넣게
		//음수 양수를 따로 관리하자
		int zeroCnt=0;
		for(int i = 0 ; i < n ; i++) {
			int tem = Integer.parseInt(br.readLine());
			if( tem > 0 ) {
				plusPQ.offer(tem);
			}else if (tem == 0) {
				zeroCnt++;
			}else {
				minPQ.offer(tem);
			}
		}
		
		//큰수끼리 곱해야 큰값이 나옴
		int result = 0;
		
		//한번 밖에 못 곱하므로 size / 2 만큼만 일단 수행
		int size = plusPQ.size()/2;
		for(int i = 0 ; i < size ; i++) {
			int temA = plusPQ.poll();
			int temB = plusPQ.poll();
			//양수는 큰값들로 곱해서 결과에 더함 -- 1 * 1 상황을 위해 max로 값 구함
			result += Math.max(temA + temB,(temA * temB));
		}
		//남은 값이 있다면 더해줌
		while( !plusPQ.isEmpty() ) {
			result += plusPQ.poll();
		}
		
		//음수 차레 --> 음수는 작은 값끼리 곱하면 더 큰 양수를 반환함
		size = minPQ.size()/2;
		for(int i = 0 ; i < size ; i++) {
			int temA = minPQ.poll();
			int temB = minPQ.poll();
			//양수는 큰값들로 곱해서 결과에 더함
			result += (temA * temB);
		}
		//남은 값이 있다면 0의 개수는 제외하고 결과값에 더함ㄴ
		while( !minPQ.isEmpty() ) {
			if( zeroCnt-- > 0 ) {
				minPQ.poll();
			}else {
				result += minPQ.poll();
			}
			
		}
		System.out.println(result);
	}
}