package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//다리놓기
public class etc_24 {
	static int n,m,result;
	static int[][] arr;
private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); //TC수
		for(int test = 0; test < T ; test++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			arr = new int[m+1][n+1];
			//조합
			result = 0;
			//bridge(0,0); //조합의 모든 경우의 수 생각
			sb.append(combi(m,n)).append("\n");
			
		}
		System.out.println(sb);
	}
	//조합의 경우의 수 점화식 풀이 == all: 전체 뽑는 수   pick: 고르는수
	private static int combi(int all, int pick) {
		if( arr[all][pick] != 0) { //값 저장되어 있으면 그 수를 활용함
			return arr[all][pick];
		}else {
			// 값이 없을때는 계산
			if( all == pick || pick == 0 ) { 
				//전체수 = 뽑는수 or 뽑는 수:0 인경우는 1가지 경우밖에 없음
				return arr[all][pick] = 1;
			}
			//메모제이션을 위해 arr 배열에 대입하면서 재귀호출함
			return arr[all][pick] = combi(all-1, pick) + combi(all-1, pick-1);
		}
		
		
	}
	//놓은 다리 수와 시작 위치 입력받음 -- 해당코드에서 쓸모없음(시간초과나옴)
	private static void bridge(int cnt, int start) {
		if( cnt == n) {
			result++;
			return;
		}
		
		for(int i = start; i < m ; i++) {
			bridge(cnt+1, i+1);
		}
		
	}
	
}

