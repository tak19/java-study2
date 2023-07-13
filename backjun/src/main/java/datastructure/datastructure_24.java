package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class datastructure_24 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 총 인원가 뺄 순번
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new LinkedList<>();

		for(int i = 1; i <= N; i++) {
			q.add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append('<');

		while(q.size() > 1) {
			//싸이클이 돌때가 아니면 뺐다가 다시 넣어줌
			for(int i = 0; i < M - 1; i++) {
				q.offer(q.poll());
			}
			//싸이클씨 출력에 추가
			sb.append(q.poll()).append(", ");
		}
 
		// 출력형식
		sb.append(q.poll()).append('>');
		System.out.println(sb);
	}
 
}