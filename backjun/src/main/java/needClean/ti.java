package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*
 * 25크기의 배열 선언하고, 25C7 구해서 조건에 맞는지 판단 + 연결되어 있는지 확인 후 ++
 */
public class ti {
	static char[] map;
	static boolean[] visit;
	static int[] member;
	static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//지도 입력받기
		map = new char[25];
		int cnt = 0;
		for(int i = 0 ; i < 5 ; i++) {
			char[] tem = br.readLine().toCharArray();
			for(int j = 0 ; j < 5 ; j++) {
				map[cnt++] = tem[j];
			}
		}

		visit = new boolean[25];
		member = new int[7];
		combi(0,0,0);

		System.out.println(result);
	}
	//7명 뽑기
	private static void combi(int cnt, int start, int yean) {
		//임도연파가 3명 이상 뽑혔다면 리턴 시켜줌 -- 조건 만족 x
		if( yean > 3 ) {
			return;
		}
		//7명 뽑음 -> 조건만족함 -- 연결되어있는지만 확인하면됨
		if( cnt == 7 ) {
			// 연결확인 -> 인덱스번호(배열값)로 -> +-1, +-5 (사방연결화인가능)
			if( ck() ) {
				result++;
			}
			return;
		}
		//25명으로 고정되어있음
		for(int i = start ; i < 25 ; i++) {
			member[cnt] = i;
			//임도연 파라면
			if( map[i] == 'Y' ) {
				combi(cnt+1, i+1,yean+1);
			}else {
				//다솜파라면
				combi(cnt+1, i+1,yean);
			}

		}

	}
	//연결되어 있나?
	private static boolean ck() {
		boolean[] connect = new boolean[7];
		//7공주만큼
		int standard = member[0]; //기준 인덱스로 하나씩 확인 - 0번기준으로
		connect[0]= true;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(standard);
		//연결되어있나?
		while( !q.isEmpty() ) {
			int tem = q.poll();
			
			//나머지 중 방문안하곳만 방문
			for(int i = 1 ; i < 7 ; i++) {
				if( !connect[i] ) {
					int num = member[i];
					if( ckCon(tem,num) ) {
						connect[i] = true;
						q.offer(num);
					}
				}
			}
		}
		//방문 안한 배열이 있다면 false 반환 -> 연결이 안되어있단말
		for(int i = 1 ; i < 7 ; i++) {
			if( !connect[i] ) {
				return false;
			}
		}
		return true;
	}
	//연결됨?
	private static boolean ckCon(int tem, int num) {
		int realM = 0;
		int realP = 0;
		//범위 재설정 -> 작아지는 쪽은 최대값으로
		if( tem - 1 < (tem / 5 * 5) ) {
			realM = tem / 5 * 5;
		}else {
			realM = tem -1;
		}
		// 다음 범위로 넘어가면 그대로유지
		if( tem + 1 >= ( (tem / 5) + 1) * 5 ) {
			realP = tem;
		}else {
			realP = tem + 1;
		}
		
		
		//4방중 한곳이라도 붙어있다면 +- 1일때 경계 범위체크 주의
		if( realM == num || realP == num || tem + 5 == num || tem - 5 == num) {
			return true;
		}
		return false;
	}
}

