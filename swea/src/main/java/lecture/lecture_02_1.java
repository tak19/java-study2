package lecture;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class lecture_02_1 {
	static boolean[] select;
	static int N,M,L;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		//TC실행
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			ArrayList<Integer> list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());

			//현재 수열 길이, 편집 수, 마지막 출력 인덱스
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			//암호문 추가함
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			//명령어 입력받음
			for(int i = 0 ; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				if( s.equals("I") ) {
					//삽입
					int index = Integer.parseInt(st.nextToken());
					int value = Integer.parseInt(st.nextToken());
					list.add(index,value);

				}else if( s.equals("D") ) {
					//삭제
					int index = Integer.parseInt(st.nextToken());
					list.remove(index);
				}else {
					//업뎃
					int index = Integer.parseInt(st.nextToken());
					int value = Integer.parseInt(st.nextToken());
					list.set(index, value);
				}
			}
			if( L  <= list.size() ) {
				sb.append(list.get(L));
			}else {
				sb.append(-1);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}