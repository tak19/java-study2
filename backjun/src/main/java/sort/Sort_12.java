package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sort_12 {
	static int N,M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 총 인원 N명과 관계 수 M
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//각 리스트를 저정할 연결 리스트 배열을 만든다.
		List<ArrayList<Integer>> list = new ArrayList<>();
		for(int i = 0 ; i <= N ; i++) {
			list.add(new ArrayList<Integer>());
		}
		//관계 입력받기
		int[] arr = new int[N+1];
		int a = 0;
		int b = 0;
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			//관계 추가하고 관계 배열 증가시킴
			list.get(a).add(b);
			arr[b]++;
		}
		
		//관계가 없는 경우 큐에 일단 삽입한다.
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1 ; i <= N ; i++) {
			if( arr[i] == 0 ) {
				q.offer(i);
			}
		}
		StringBuilder sb = new StringBuilder();
		//관계 큐가 빌때까지 반복
		while( !q.isEmpty() ) {
			//해당 인덱스를 빼주고 출력에 추가
			int index = q.poll();
			sb.append(index).append(" ");
			//해당 노드와 연결된 관계 배열에서 감소 시키고 0 이 되면 큐에 삽입
			for( Integer i : list.get(index) ) {
				arr[i]--;
				if( arr[i] == 0 ) {
					q.offer(i);
				}
			}
		}
		System.out.println(sb);
		
		
	}
}