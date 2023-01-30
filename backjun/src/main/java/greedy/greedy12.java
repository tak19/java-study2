package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class greedy12 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] time = new int[n][2];
		for(int i = 0 ; i < n ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i][0] = Integer.parseInt(st.nextToken()); 
			time[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(time, (o1, o2) -> {
			return o1[0]!=o2[0] ? o1[0]-o2[0] : o1[1]-o2[1]; // 첫번째 기준 오름차순 > 두번째 기준 오름차순
		});

		//우선순위 큐를 이용해서 작은 수가 우선순위를 가르치도록함
		PriorityQueue<Integer> q = new PriorityQueue<>();
		q.add(time[0][1]);
		
		//큐에 들은 값이 최소임... 해당 수업이 끝나는 시간이랑 수업 시작 시간이 같거나 크면 같은 강의실 이용가능
		for(int i = 1 ; i < n ; i++) {
			if( q.peek() <= time[i][0] ) {
				q.poll();
			}
			q.add(time[i][1]);
		}
		
		System.out.println(q.size());
	}

}
