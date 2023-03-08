package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class greedy12 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		//카드 묶음 집어넣기
		for(int i = 0 ; i < n ; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		int result = 0;
		//n-1번까지 반복 -> 카드 뭉치 중 가장 유사한 크기끼리만 비교해주면 됨
		for(int i = 1 ; i < n ; i++) {
			int temA = pq.poll();
			int temB = pq.poll();
			result += temA+temB;
			pq.offer(temA+temB);
		}
		System.out.println(result);

	}
}