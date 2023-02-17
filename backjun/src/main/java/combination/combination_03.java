package combination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class combination_03 {
	private static StringBuilder sb = new StringBuilder();
	static int n,m;
	static int result = Integer.MAX_VALUE;
	static int ckAll,homeAll;
	static int[][] map;
	static Pos[] p, home, comP;
 
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//N과 M입력받음
		n = Integer.parseInt(st.nextToken()); // 지도크기
		m = Integer.parseInt(st.nextToken()); //남은닭 가게 수

		List<Pos> list = new ArrayList<>();
		List<Pos> homeList = new ArrayList<>();
		//지도 저장한다.  이때 필요한거 닭 가게 수
		map = new int[n+1][n+1]; 
		for(int i = 1; i <= n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//닭 가게라면 counting
				if( map[i][j] == 2 ) {
					ckAll++;
					list.add(new Pos(i,j));
					
				}else if( map[i][j] == 1 ) { //집이라면!!
					homeAll++;
					homeList.add(new Pos(i,j));
				}
			}
		}
		//집을 배열로 변환
		home = homeList.toArray(new Pos[homeAll]);
		
		//닭 가게 좌표 배열 만듬 -- 이제 p 길이만큼 조합으로 뽑는다!
		p = list.toArray(new Pos[ckAll]);
		//뽑은 닭집을 저장할꺼야!!
		comP = new Pos[m];
		//그냥 조합 사용함
		//combi(0,0);

		//NP사용해보자		
		int[] input = new int[ckAll]; // 모든 닭집
		int cnt = 0;
		while( ++cnt <= m) {
			input[ckAll - cnt] = 1;
		}

		do {
			int now = 0;
			for(int i = 0 ; i < ckAll ; i++) {
				if( input[i] == 1) {
					comP[now] = p[i];
					now++;
				}
			}
			
			chDisCal();
			
		} while (npCom(input));
	
		System.out.println(result);


	}
	private static boolean npCom(int[] input) {
		int n = input.length;
		
		int i = n - 1;
		while( i > 0 && input[i-1] >= input[i]) {
			i--;
		}
		if( i == 0) {
			return false;
		}
		
		int j = n - 1;
		while( input[i-1] >= input[j] ) {
			j--;
		}
		swap(input,i-1,j);
		
		int k = n - 1;
		while( i < k ) {
			swap(input,i++,k--);
		}
		
		
		return true;
	}
	private static void swap(int[] input, int i, int j) {
		int tem = input[i];
		input[i] = input[j];
		input[j] = tem;
	}
	private static void combi(int cnt, int start) {
		if( cnt == m ) {
			
			chDisCal(); //도시 치킨거리 찾자
			return;
		}
		
		for(int i = start ; i < ckAll ; i++) {
			comP[cnt] = p[i]; //조합에 하나넣음
			combi(cnt+1, i+1); //개수 증가하고 다음 치킨 뽑아!
		}
		
	}
	private static void chDisCal() {
		
		int tempDis = 0;
		//집을 하나씩 돌면서 가장 가까운 치킨집 찾아요
		for(int i = 0 ; i < homeAll ; i++) {
			Pos homePos = home[i]; //첫번째 집부터 방문하요
			int min = Integer.MAX_VALUE; //거리를 최대로 설정
			
			for(int j = 0 ; j < m ; j++) {
				Pos chiPos = comP[j];
				//현재 저장되어있는 min이랑 최근거리랑 비교 후 더 작으면 저장
				min =Math.min(min, Math.abs(homePos.x - chiPos.x) + Math.abs(homePos.y - chiPos.y));
			}
			//한집 기준 치킨집 다 돌면 최소값이 정해져있겠지요? 그럼 전체 거리를 저장하는 곳에 저장
			tempDis += min;
		}
		// 모든 집 돌면 tempDis에 치킨거리 저장되어있겠지요?
		result = Math.min(result, tempDis);
	}

	//좌표 클래스
	static class Pos{
		int x;
		int y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
