package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sort_13 {
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//전체 건물 수
		N = Integer.parseInt(br.readLine());
		
		//각 연결 요소를 저장할 ArrayList를 만든다.
		List<ArrayList<Integer>> list = new ArrayList<>();
		for(int i = 0 ; i <= N ; i++) {
			list.add(new ArrayList<>());
		}
		
		//건물 수 만큼의 배열 선언
		int[] connect = new int[N+1];
		int[] time = new int[N+1];
		
		
		StringTokenizer st = null;
		int index = 0;
		// 각 건물의 관계를 입려받는다.
		for(int i = 1 ; i <= N ; i++) {
			// 각 건물이 짓는데 걸리는 시간과 선,후 관계를 입력받음
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			
			while( true ) {
				// 자기보다 먼저 지어져야하는 건물 번호 입력받음
				index = Integer.parseInt(st.nextToken());
				if( index == -1 ) {
					break;
				}
				//선수 건물의 수 만큼 자신 배열을 증가, 해당 건물과 연결점을 추가한다.
				connect[i]++;
				list.get(index).add(i);
			}
		}
		
		//결과를 저장할 배열 선언
		int[] result = new int[N+1];
		
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1 ; i <= N ; i++) {
			//진입 차수가 0인 즉, 그냥 혼자 지을 수 있는 건물이라면  인덱스를 큐에 삽입
			if( connect[i] == 0 ) {
				q.add(i);
			}
		}
		
		
		//모든 요소 방문할때까지
		while( !q.isEmpty() ) {
			int visit = q.poll();
			//해당 인덱스와 연결되어있는 노드 방문
			for( Integer i : list.get(visit) ) {
				connect[i]--;
				//결과값에   현재 결과  vs  (이전 건물의 대기시간 + 대기시간) 중 최대값을 삽입
				result[i] = Math.max(result[i], result[visit] + time[visit]);
				//진입 차수가 0 이라면, 즉 지을 수 있는 건물 이라면
				if( connect[i] == 0 ) {
					q.add(i);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1 ; i <= N ; i++) {
			sb.append(result[i] + time[i]).append("\n");
		}
		System.out.println(sb);
		
	}
}