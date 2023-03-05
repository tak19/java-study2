package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class bruteforce_08 {
	static int N, result;
	static int[] sequence,output;
	static boolean[] isSelect;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//이닝 수
		N = Integer.parseInt(br.readLine());

		//이닝 결과 입력
		StringTokenizer st;
		map = new int[N][9];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		isSelect = new boolean[9];
		sequence = new int[9];
		isSelect[0]= true;
		sequence[3] = 0; 
//		pec(0); //그냥 순열
		
		//NP 용
		output = new int[8];
		for(int i = 0 ; i < 8 ; i++) {
			output[i] = i+1;
		}
		
		do {
			sequence[3] = 0; //4번타자 1번타자로 고정
			for(int i = 0 ; i < 8 ; i++) {
				if( i >= 3 ) {
					sequence[i+1] = output[i];
				}else {
					sequence[i] = output[i];
				}
				
			}
			countPoint(0,0,0);
		} while (np(output));
		
		
		System.out.println(result);
	}
	//NP 순열
	private static boolean np(int[] output) {
		int n = output.length;
		int i = n - 1;
		while( i > 0 && output[i] <= output[i-1] ) { //자기보다 작은값 있다면 인덱스 이동
			i--;
		}
		if( i == 0 ) {
			return false;
		}
		
		int j = n - 1;
		while( output[i-1] >= output[j] ) {
			j--;
		}
		
		swap(output,i-1,j);
		
		int k = n - 1;
		while( k > i ) {
			swap(output, k--, i++);
		}
		
		return true;
	}
	//자리 교환
	private static void swap(int[] output, int i, int j) {
		int tem = output[i];
		output[i] = output[j];
		output[j] = tem;
	}

	//타순 생성 
	private static void pec(int cnt) {
		if( cnt == 8 ) {
			//매개변수: 현재 순서,점수,이닝 
			countPoint(0,0,0);

			return;
		}

		for(int i = 1 ; i < 9 ; i++) {
			//뽑혔으면 제외
			if( isSelect[i] ) {
				continue;
			}
			if( cnt >= 3) {
				sequence[cnt+1] = i;
				isSelect[i] = true;
				pec(cnt+1);
				isSelect[i] = false;
			}else {
				sequence[cnt] = i;
				isSelect[i] = true;
				pec(cnt+1);
				isSelect[i] = false;
			}
		}
	}

	//점수 계산함
	private static void countPoint( int nowNum,int point, int ining) {

		//sequence에 타순이 저장되어 있음
		int outCnt = 0;
		int getPoint = 0;
		int[] runner = new int[5];
		//종료 조건 -- 마지막 이닝 마무리
		if( ining == N ) {
			result = Math.max(result, point);
			return;
		}
		//3아웃까지 - outCnt매개변수 필요있을까?
		while( outCnt < 3 ) {

			if( map[ining][ sequence[nowNum] ] == 0 ) {
				//아웃 이라면
				outCnt++;
			}else if( map[ining][ sequence[nowNum] ] == 1 ) {
				//1루타라면 -- 주자정보와 타 정보 입력
				getPoint += Advancebase(runner,1);

			}else if( map[ining][ sequence[nowNum] ] == 2 ) {
				//2루타
				getPoint += Advancebase(runner,2);

			}else if( map[ining][ sequence[nowNum] ] == 3 ) {
				//3루타
				getPoint += Advancebase(runner,3);

			}else if( map[ining][ sequence[nowNum] ] == 4 ) {
				//4루타
				getPoint += Advancebase(runner,4);

			}

			nowNum = ( ++nowNum % 9 ); //타순 증가
		}
		//주자정보와 득점정보, 이닝을 추가하고 진행

		countPoint(nowNum, point + getPoint, ining + 1 );

	}
	//진루 - 주자와 성적
	private static int Advancebase(int[] runner, int num) {
		int getPoint = 0;
		runner[0] = 1;
		for(int i = 3 ; i >= 0 ; i --) {
			//타자가 있다면 -> 해당 타수 만큼 진루하고, 해당 베이스는 초기화함
			if( runner[i] != 0 ) {
				if( canGo(i+num) ) { // 베이스 내 이동 가능한가?
					
					runner[i+num] = 1;
				}else {
					
					getPoint++;
				}
				runner[i] = 0; //진루 후 해당 베이스 초기화
			}
		}
		return getPoint;

	}
	//배이스 안으로 이동하는지 -> 벗어난다면 득점임
	private static boolean canGo(int i) {
		if( i < 4 ) {
			return true;
		}
		return false;
	}
}