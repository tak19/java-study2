package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_08 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int start = Integer.parseInt(st.nextToken());
		int goal = Integer.parseInt(st.nextToken());

		int[] visit = new int[100001];


		int result = 0;

		//같으면 0
		if (start == goal) {

		}else if(start > goal ) { //시작 값이 더 크면 두 수의 차가 최단거리임!
			result = start - goal;
		}else {
			//BFS 실시
			Queue<Integer> q = new LinkedList<>();
			q.add(start);
			visit[start] = 1;
			while (!q.isEmpty()) {
				int now = q.poll();

				//for문을 통해 +1. -1, x2 를 진행
				for (int i = 0; i < 3; i++) {
					int next;

					if (i == 0) {
						next = now + 1;
					} else if (i == 1) {
						next = now - 1;
					} else {
						next = now * 2;
					}

					if (next == goal) {
						System.out.println(visit[now]);
						return;
					}

					//다음 수가 범위 내에 있고 방문하지 않았던 수라면!!
					if (next >= 0 && next < 100001 && visit[next] == 0) {
						q.add(next);
						visit[next] = visit[now] + 1;
					}
				}

			}

		}
		System.out.println(result);
	}
}






