package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_10 {
	static boolean[] visit;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for(int t = 0 ; t < test ; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int goal = Integer.parseInt(st.nextToken());

			String result =""; //결과저장
			visit = new boolean[10000];

			Queue<Pos> q = new LinkedList<>();
			q.add(new Pos(start,""));
			visit[start] = true;
			//BFS 탐색 시작
			while( !q.isEmpty() ) {
				Pos pos = q.poll();
				int data = pos.data;
				String cal = pos.cal;
				//초기에 갑을 확인
				if(data == goal) {
					result = cal;
					break;
				}

				//DSLR 연산
				//D --> 2*N의 10000 나머지
				if( !visit[2*data % 10000] ) {
					visit[2*data % 10000] = true;
					q.add(new Pos(2*data % 10000, cal+"D"));
				}
				//S --> n에서 -1 한값 출력 if) 0이라면 9999
				if( data == 0) {
					if( !visit[9999] ) {
						visit[9999] = true;
						q.add(new Pos(9999, cal+"S"));
					}
				}else {
					if( !visit[data - 1 ] ) {
						visit[data - 1] = true;
						q.add(new Pos(data - 1, cal+"S"));
					}
				}

				//L 수행전 돌리기 -왼쪽 -- 첫번째 자리가 끝으로 가고 나머지 자리는 밀어준다.
				int l = (data % 1000) * 10 +(data / 1000);
				//L
				if( !visit[l] ) {
					visit[l] = true;
					q.add(new Pos(l, cal+"L"));
				}

				//R 수행전 돌리기 -오른쪽 --마지막 자리가 앞으로 가고 나머지 자리 밀어준다.
				int R = (data % 10) * 1000 + (data / 10) ;
				//R
				if( !visit[R] ) {
					visit[R] = true;
					q.add(new Pos(R, cal+"R"));
				}
			}

			sb.append(result + "\n");

		}
		System.out.println(sb);
	}

	static class Pos{
		int data;
		String cal;
		Pos(int data, String cal){
			this.data =data;
			this.cal = cal;

		}
	}
}
