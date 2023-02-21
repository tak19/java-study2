package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bruteforce_04 {
	static boolean[][] isPlay;
	static Pos[] inputs; //입력받을꺼
	static Pos[] results; //진행상황 저장
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t = 0 ; t < 4; t ++) {
			st = new StringTokenizer(br.readLine());
			//경기 진행 여부 - 앞선 경기는 할 필요가 없음
			isPlay = new boolean[][]{ { true, false, false, false, false, false},
									  { true, true, false, false, false, false},
									  { true, true, true, false, false, false},
									  { true, true, true, true, false, false},
									  { true, true, true, true, true, false},
									  { true, true, true, true, true, false}
			 };
			 //결과를 저장할꺼야
			results = new Pos[6];
			for(int i = 0 ; i < 6 ; i++) {
				results[i] = new Pos(0,0,0);
			}
			ans = 0;
			inputs = new Pos[6];
			for(int i = 0 ; i < 6 ; i++) {
				int win = Integer.parseInt(st.nextToken()); //승
				int draw = Integer.parseInt(st.nextToken()); //무
				int lose = Integer.parseInt(st.nextToken()); //패
				
				inputs[i] = new Pos(win,draw,lose); //입력받은 승무패 저장함
			}
			//A팀부터 경기 시작
			play(0,0);
			sb.append(ans).append(" ");
		}
		System.out.println(sb);
		
		
	}
	private static void play(int home, int away) {
		
		if( home == 5 && away == 5 ) { //경기를 끝까지 했다면 최종 비교 후 다음 탐색
			
			int[] winCnt = new int[2];
			int[] drawCnt = new int[2];
			int[] loseCnt = new int[2];
			//입력 기록과 진행해온 기록을 비교한다.
			for(int i = 0 ; i < 6 ; i++) {
				winCnt[0] += inputs[i].win;
				drawCnt[0] += inputs[i].draw;
				loseCnt[0] += inputs[i].lose;
				
				winCnt[1] += results[i].win;
				drawCnt[1] += results[i].draw;
				loseCnt[1] += results[i].lose;
			}
			//승무패 기록이 모두 일치한다면
			if( winCnt[0] == winCnt[1] && drawCnt[0] == drawCnt[1] && loseCnt[0] == loseCnt[1] ) {
				ans = 1;
			}
			return;
			
		}
		if(away > 5) {
			//입력과 진행상황의 승무패가 같다면
			if( results[home].win == inputs[home].win && results[home].draw == inputs[home].draw &&results[home].lose == inputs[home].lose ) {
				if( results[home].win + results[home].draw + results[home].lose == 5 ) { //5경기 진행했어야지!!
					play(home+1,0);
				}
			}
			//입력조건과 맞지 않다면 해당부분에서 가지치기
			return;
			
		}
		//진행한 경기라면 다음경기하러감
		if( isPlay[home][away] ) {
			play(home,away+1);
			return;
		}
		
		isPlay[home][away] = true;
		
		//승리 체크 후 다음 방문
		results[home].win += 1;
		results[away].lose += 1;
		play(home,away+1);
		results[home].win -= 1;
		results[away].lose -= 1;
		
		//무승부 체크
		results[home].draw += 1;
		results[away].draw += 1;
		play(home,away+1);
		results[home].draw -= 1;
		results[away].draw -= 1;
		
		//패배 체크
		results[home].lose += 1;
		results[away].win += 1;
		play(home,away+1);
		results[home].lose -= 1;
		results[away].win -= 1;
		
		isPlay[home][away] = false;
	}
	static class Pos{
		int win,draw,lose;
		Pos(int win, int draw, int lose){
			this.win = win;
			this.draw = draw;
			this.lose = lose;
		}
	}
}