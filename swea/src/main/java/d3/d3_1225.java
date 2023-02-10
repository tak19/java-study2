package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class d3_1225 {
private static StringBuilder sb = new StringBuilder();
//SWEA 암호생성기
public static void main(String[] args) throws Exception {
	/*
	 * 1. 입력파일 읽어 들이기
	 */
	System.setIn(new FileInputStream("res/input2.txt"));
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	/*
	 * 2. 입력파일 객체화
	 */
	
	for(int test_case = 1 ; test_case <= 10 ; test_case++) {
		br.readLine();
		sb.append("#" + test_case + " ");
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 0 ; i < 8 ; i++) {
			q.add(Integer.parseInt(st.nextToken()));
		}
		int dis = 1; //감소값
		int tem = 0; //임시변수
		
		while( (q.peek() - dis) > 0 ) {
			
			tem = q.poll() - dis;
			q.offer(tem);
			
			if( dis % 5 == 0) {
				dis = 0;
			}
			dis++;
		}
		q.poll();
		q.add(0);
		
		for(int i = 0 ; i < 8 ; i++) {
			sb.append(q.poll() + " ");
		}
		sb.append("\n");
	}
	
	/*
	 * 3. 알고리즘 풀기
	 */
	
	/*
	 * 4. 정답출력
	 */
	System.out.println(sb);
	
}
}
