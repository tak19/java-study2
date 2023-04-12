package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class datastructure_04 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		//TC만큼 반복
		for(int test = 0; test < T ; test++) {
			TreeMap<Integer,Integer> tMap = new TreeMap<>();
			//연산횟수
			int N = Integer.parseInt(br.readLine());

			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				String io = st.nextToken();
				//삽입 연산은 I , 삭제 연산은 D로 표시함
				if( io.equals("I") ) {
					//입력값
					int input = Integer.parseInt(st.nextToken());
					//입력값을 Key로 사용한다 --> getOrDefault: 해당 키값의 값을 불러오는데 값이 없으면 설정한 Defalt값 반환함
					tMap.put(input,  tMap.getOrDefault(input, 0) + 1 );

				}else {
					//트리가 비어있다면 값 빼는 과정 생략
					if( tMap.isEmpty() ) {
						continue;
					}
					int outKey = 0;
					int output = Integer.parseInt(st.nextToken());
					//제거할 순서를 저장함
					//제거할 순서를 저장함
					if( output == -1 ) {
						//최소값 빼기
						outKey = tMap.firstKey();
					}else {
						//최대값 빼기
						outKey = tMap.lastKey();
					}
					//해당 키 값의 개수가 제거함으로써 없어진다면 트리맵에서 제거
					if( tMap.put(outKey, tMap.get(outKey) - 1 ) == 1 ) {
						tMap.remove(outKey);
					}
				}
			}
			if( tMap.isEmpty() ) {
				sb.append("EMPTY").append("\n");
			}else {
				sb.append(tMap.lastKey()).append(" ").append(tMap.firstKey()).append("\n");
			}
		}
		
		System.out.println(sb);

	}

}