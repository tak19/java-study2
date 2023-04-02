package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class greedy14 {
	static long N,K,result;
	static int[] bag;
	static PriorityQueue<Jewel> pq,selectPq;
	static Stack<Jewel> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken()); //보석 수
		K = Long.parseLong(st.nextToken()); //가방 수
		
		//무게가 가벼운 것(오름차순) 우선 거내는데 무게가 같다면 값이 큰것을 우선 꺼냄
		pq = new PriorityQueue<>( (o1,o2) -> {
			return o2.w == o1.w ? o2.cost - o1.cost : o1.w - o2.w;
		});

		//보석 입력받음
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			pq.offer(new Jewel(w,c));
		}
		
		//가방에 허용된 무게를 입력받음
		bag = new int[(int) K];
		for(int i = 0; i < K ; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		
		//오름차순으로 정렬함
		Arrays.sort(bag);
		
		// 값이 큰것부터 빼내는데 값이 같다면 무게가 무거운것을 먼저 빼냄
		selectPq = new PriorityQueue<>( (o1,o2) -> {
			return o2.cost == o1.cost ? o2.w - o1.w : o2.cost - o1.cost;
		});
		
		//작은 가방부터 가장 큰 가치를 담을 수 있는걸 담는다..
		for(int i = 0 ; i < K ; i++) {
			//현재 제한된 가방 무게에서 담을 수 있는 모든 보석을 우선순위 큐에 넣는다.
			while( !pq.isEmpty() && bag[i] >= pq.peek().w ) {
				selectPq.offer(pq.poll());
			}
			// 현재 담을 수 있는 보석 중에 가치가 가장 큰 보석을 빼낸다
			if( !selectPq.isEmpty() ) {
				result += selectPq.poll().cost;
			}
			
		}
		System.out.println(result);		
	}

	//보석 정보 w: 무게 cost: 가치
	static class Jewel{
		int w, cost;

		public Jewel(int w, int cost) {
			this.w = w;
			this.cost = cost;
		}
		
	}
	
}