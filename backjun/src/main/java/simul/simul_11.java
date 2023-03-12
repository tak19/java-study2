package simul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class simul_11 {
	static char[][] s;
	static int[] realDir;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//톱니바퀴 입력받기
		s = new char[5][8]; //인덱스 그대로 사용
		for(int i = 0 ; i < 4 ; i++) {
			s[i+1] = br.readLine().toCharArray();
		}
		//이동횟수
		int moveCnt = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		//회전시작
		for(int i = 0 ; i < moveCnt; i++) {
			st = new StringTokenizer(br.readLine());
			//회전시킬 톱니 번호와 방향
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			realDir = new int[5]; // 톱니별 회전 방향
			realDir[num] = dir; //자산의 회전 입력함
			ckRotation(num);		
			platRotation();
			
		}
		int result = 0;
		int weight = 1;
		for(int k = 1 ; k < 5 ; k++) {
			if( s[k][0] == '1' ) {
				result += weight; 
			}
			weight *= 2;
		}
		System.out.println(result);

	}
	//회전시킴
	private static void platRotation() {
		for(int i = 1 ; i < 5 ; i++) {
			if( realDir[i] == 0 )	continue;
			
			if( realDir[i] == 1 ) { //시계회전
				// 7-> 1 로 온다
				char tem = s[i][7]; //따로빼둠
				//시계방향으로 한칸씩 민다
				for(int k = 7 ; k > 0 ; k--) {
					s[i][k] = s[i][k-1];
				}
				s[i][0] = tem; //마지막 자리 0에 저장
				
			}else if( realDir[i] == -1 ) { //반시게 회전
				
				// 1-> 7 로감
				char tem = s[i][0]; //따로빼둠
				//시계방향으로 한칸씩 민다
				for(int k = 0 ; k < 7 ; k++) {
					s[i][k] = s[i][k+1];
				}
				s[i][7] = tem; //마지막 자리 0에 저장
				
			}
			
		}
	}
	//회전되는 톱니를 확인 -> 2번이랑 6번 맞닿아있는지
	private static void ckRotation(int num) {
		//왼쪽방향 체크 - 자신의 6번이랑 상대의 2번
		for(int i = num ; i > 1 ; i--) {
			if( s[i][6] != s[i-1][2] ) {
				realDir[i-1] = (realDir[i] == 1 ? -1 : 1); //다른 극이라면 다른 회전함
			}else {
				//같은 극이면 탐색 종료
				break;
			}
		}
		//오른쪽방향 체크
		for(int i = num ; i < 4 ; i++) {
			if( s[i][2] != s[i+1][6] ) {
				realDir[i+1] = (realDir[i] == 1 ? -1 : 1); //다른 극이라면 다른 회전함
			}else {
				//같은 극이면 탐색 종료
				break;
			}
		}
	}
}


