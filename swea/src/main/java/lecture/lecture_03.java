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

public class lecture_03 {
	static boolean[] select;
	static int N,M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		//TC실행
		for(int test = 1 ; test <= 10 ; test++) {
			sb.append("#").append(test).append(" ");
			ArrayList<Integer> list = new ArrayList<>();
			//현재 암호문 길이
			N = Integer.parseInt(br.readLine());
			
			//암호문 추가함
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			//명령어 횟수
			int comand = Integer.parseInt(br.readLine());
			//명령어 입력받음
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < comand; i++) {
				ArrayList<Integer> tem = new ArrayList<>();
				String s = st.nextToken();
				if( s.equals("I") ) {
					//삽입
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for(int j = 0 ; j < y ; j++) {
						tem.add(Integer.parseInt(st.nextToken()));
					}
					list.addAll(x,tem);
				}else if( s.equals("A") ) {
					//추가
					int y = Integer.parseInt(st.nextToken());
					for(int j = 0 ; j < y ; j++) {
						tem.add(Integer.parseInt(st.nextToken()));
					}
					list.addAll(tem);
				}else {
					//삭제
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for(int j = 0 ; j < y ; j++) {
						list.remove(x);
					}
				}
			}
			for(int i = 0 ; i < 10 ; i++) {
				sb.append(list.get(i)).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}