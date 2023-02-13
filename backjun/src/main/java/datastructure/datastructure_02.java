package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class datastructure_02 {
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		/*
		 * 1. 입력파일 읽어 들이기
		 */
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		Stack<Top> s = new Stack<>(); //기둥을 저장할 스택
		//기둥 배열 저장
		for(int i = 1 ; i <= n ; i++) {
			//높이를 차례대로 입력받는다.
			int height = Integer.parseInt(st.nextToken());
			
			if( s.isEmpty() ) {
				sb.append(0).append(" ");
				s.push(new Top(i,height));
			}else {

				while( true ) {
					if( s.isEmpty()) {
						sb.append(0).append(" ");
						s.push(new Top(i,height));
						break;
					}

					Top t = s.peek();
					if (t.height > height) { // 이전의 탑 높이가 현재 탑의 높이보다 높다면, 송신가능함
						sb.append(t.no + " "); // 해당 기둥 번호 저장함.
						s.push(new Top(i, height)); // 현재 탑을 스택에 push한다.
						break;
					} else { // 이전 탑의 높이가 현재 탑의 높이보다 낮다면, -> 전파 어차피 못주고 못받음
						s.pop(); // 스택에서 pop하고 다시 반복문을 돌린다.
					}
				}
			}

		}


		System.out.println(sb);


	}
	static class Top{
		public int no;  // 탑의 번호: 숫자가 작을수록 왼쪽에 위치 
		public int height;  // 탑의 높이

		public Top(int no, int height) {
			this.no = no;
			this.height = height;
		}

	}

}

