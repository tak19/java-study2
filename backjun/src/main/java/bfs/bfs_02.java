package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_02 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //정점 수
		int v = Integer.parseInt(st.nextToken()); //간선 수

		//원소 인덱스를 그대로 쓰기위해 n+1
		List<Integer>[] list = new ArrayList[n+1];
		//q = new LinkedList[n+1];
		for(int i = 0 ; i <= n ; i++) {
			list[i] = new ArrayList<>();
		}

		for(int i = 0 ; i < v ; i++) {
			st =new StringTokenizer(br.readLine());
			int fir = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			list[fir].add(two);
			list[two].add(fir);
			//무방향 이기 때문에 양쪽다 진행 ==> 원소가 있는 지 확인하고 없으면 넣어준다.
			//확인하고 넣어주니 타임에러 발생함.. 있는지 확인하는 작업이 더 느리군!!
//			if(!list[fir].contains(two)) {
//				list[fir].add(two);
//			}
//			if(!list[two].contains(fir)) {
//				list[two].add(fir);
//			}
		}

		int total=0;
		boolean[] visit = new boolean[n+1];
		for(int i = 1 ; i <= n ; i++) {
			//이전에 방문하지 않은 곳이라면!!!
			if( !visit[i] ) {
				Queue<Integer> q = new LinkedList<>();
				//큐에 해당 인덱스의 리스트값 다 넣음!!
				q.offer(i);
				//방문 x라면 큐가 빌때까지 방문하면서 체크한다.
				while(!q.isEmpty()) {
					
					int num = q.poll();
					if(!visit[num]) {
						visit[num] = true; //방문처리
						
						for (int sea : list[num]) {
							if (!visit[sea]) {
								q.offer(sea);
							}
						}
						
						for(int j=0; j < list[num].size(); j++) {
							int sub = list[num].get(j);
							if(!visit[sub]) {
								q.add(sub);	
							}
							
						}
						
					}
				}
				total++;
			}else {
				continue;
			}

			
		}
		System.out.println(total);


	}

}