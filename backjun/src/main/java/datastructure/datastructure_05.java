package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class datastructure_05 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		//난이도와 문제 TreeSet저장할꺼임
		TreeSet<Problem> tS = new TreeSet<Problem>( (o1,o2) ->{
			return o1.level != o2.level ? o1.level - o2.level : o1.num - o2.num;
		} ) ;
		Map<Integer,Integer> map = new HashMap<>();
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken()); // 문제번호
			int L = Integer.parseInt(st.nextToken()); // 난이도
			 
			//문자번호와 난이도를 저장
			tS.add(new Problem(P, L));
			map.put(P, L);
		}
		
		//명령 개수
		int re = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < re ; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			//문제 추기
			if( order.equals("add") ) {
				//L 난이도에 P문제 추가함
				int P = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				
				//문자번호와 난이도를 저장
				tS.add(new Problem(P, L));
				map.put(P, L);
				
			}else if( order.equals("recommend") ) {
				//문제 추천
				int T = Integer.parseInt(st.nextToken());
				if( T == 1 ) {
					//난이도가 가장 어려운것 출력 == 같다면 문제번호 큰것
					sb.append(tS.last().num).append(" \n");
				}else {
					//난이도가 가장 쉬운것 출력 == 같다면 문제번호 작은것
					sb.append(tS.first().num).append(" \n");
				}
			}else {
				//문제 뽑기 -- 제거함(Solved)
				int num = Integer.parseInt(st.nextToken()); //뽑을 문제 번호
				//해당 키값으로 접근해서 큐에있는 값 뺌
				tS.remove(new Problem(num, map.get(num)));
				map.remove(num);
			}
		}
		System.out.println(sb);
		
	}
	//문제 번호와 난이도
	static class Problem{
		int num, level;

		public Problem(int num, int level) {
			this.num = num;
			this.level = level;
		}
		
	}
}