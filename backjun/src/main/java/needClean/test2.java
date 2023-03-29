package needClean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 * 대각선방향으로 진행된다...
 * 두 x,y의 합으로 인덱스를 지정해서 List 배열에 저장한다.
 */
public class test2 {
	static int N,result,total;
	static int map[][];
	static boolean[][] possible;
	static List<int[]>[] listBishop;
	static int[] isSelect;
	static Stack<int[]> queue,temp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //체스판 크기
		
		//사선방향을 저장할 리스트 뽑음
		int range = (2*N)-1;
		listBishop = new ArrayList[range];
		for(int i = 0 ; i < range ; i++ ) {
			listBishop[i] = new ArrayList<>();
		}
		
		//체스판을 입력받아유~
		StringTokenizer st = null;
		map = new int[N][N];
		possible = new boolean[N][N];
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if( map[i][j] == 1 ) {
					listBishop[i+j].add(new int[] {i,j});
				}
			}
		}
		//뽑았는지 확인하기 위한 배열
		isSelect = new int[range];
		//행-뽑은 수-남은 수 전달
		positionBishop(0, 0, 0);
		

		//		queue = new ArrayDeque<>();
		//		temp = new ArrayDeque<>();
//		queue = new Stack();
//		temp = new Stack();
//		// 완탐해봄 - 비숍을 넣을 수 있는 칸을 시작점으로 하나씩 완탐
//		for(int i = 0 ; i < N; i++) {
//			for(int j = 0 ; j < N ; j++) {
//				if( map[i][j] == 1 ) {
//					positionBishop(i,j,0,total--);
//					queue.clear();
//					temp.clear();
//				}
//			}
//		}
		System.out.println(result);

	}
	
	private static void positionBishop(int row, int cnt, int remain) {

		if( result > cnt + remain ) {
			return;
		}

		//다 뽑았다면!!!
		if( row >= N ) {
			//print(cnt);
			result = Math.max(result, cnt);
			return;
		}

		//뽑은적 없는 사선이라면!!
		for( int[] tem : listBishop[row] ) {
			if( isSelect[tem[0] + tem[1]] == 0 ) {
				isSelect[tem[0] + tem[1]] = 1;
				positionBishop(row+2, cnt+1, remain+1);
				isSelect[tem[0] + tem[1]] = 0;
			}
		}
		positionBishop(row+2, cnt, remain+1);

	}
	
	
	
//	// 놓을 수 있는 경우도 안 놓고 진행해야 하는 경우가 있을 수도 있음
//	private static void positionBishop1(int row, int col, int cnt, int remain) {
//
//		if( result > cnt + remain ) {
//			return;
//		}
//
//		//다 뽑았다면!!!
//		if( row >= N ) {
//			//print(cnt);
//			result = Math.max(result, cnt);
//			return;
//		}
//		//해당 열 다 탐색 했다면 다음행 탐색함
//		if( col >= N ) {
//			positionBishop(row+1, 0, cnt,remain);
//			return;
//		}
//		//비숍이 위치할 수 있는 칸이라면
//		if( map[row][col] == 1  ) {
//			if( !possible[row][col] ) {
//				if( ckBishop(row, col) ) {
//					moveBishop();
//					//중복되는 칸이 없다면 해당 칸에 비숍을 넣던가 말던가 해야함
//					int[] arr = new int[2];
//					arr[0] = row;
//					arr[1] = col;
//					paint(arr);
//					queue.add(arr);
//					//다음열을 탐색하는데 비숍의 크기 하나 더해줌 뽑았으니까 cnt 증가
//					positionBishop(row,col+1,cnt+1,remain-1);
//					reversePaint(queue.pop());
//					positionBishop(row,col+1,cnt,remain-1);
//				}else {
//					//중복되는 칸이 있다면 다음 열 탐색
//					moveBishop();
//					positionBishop(row,col+1,cnt,remain-1);
//				}
//			}else {
//				positionBishop(row,col+1,cnt,remain-1);
//			}
//			
//		}else {
//			//비숍이 놓일 수 없는 칸이라면 다음 열 탐색
//			positionBishop(row,col+1,cnt,remain);
//		}
//
//
//	}
	private static void reversePaint(int[] arr) {
		int cha = Math.abs(arr[0] - arr[1]);

		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N ; j++) {
				if( cha == Math.abs(i-j) ) {
					possible[i][j] = false;
				}
			}
		}
	}
	//못가는칸임!
	private static void paint(int[] arr) {
		int cha = Math.abs(arr[0] - arr[1]);

		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N ; j++) {
				if( cha == Math.abs(i-j) ) {
					possible[i][j] = true;
				}
			}
		}

	}
	//해당 위치 대각선에 비숍이 있는지 판단한다.
	private static boolean ckBishop(int i, int j) {

		//현재까지 뽑은 비숍이랑 현재 뽑을 비숍을 확인한다.
		while( !queue.isEmpty() ) {
			//int[] arr = queue.poll();
			int[] arr = queue.pop();
			temp.add(arr);
			//현재 위치랑 뽑힌 비숍의 위치를 비교
			if( Math.abs(arr[0] - i ) == Math.abs( arr[1] - j )  ) {
				return false;
			}
		}
		return true;
	}

	//큐에 있는 비숍 옮김
	private static void moveBishop() {
		while( !temp.isEmpty() ) {
			//			queue.offer(temp.poll());
			queue.add(temp.pop());
		}
	}

	private static void print(int cnt) {
		if( cnt == 7 ) {
			while( !queue.isEmpty() ) {
				//int[] a = queue.poll();
				int[] a = queue.pop();
				System.out.println(a[0] + " " + a[1] );
			}
			System.out.println("ddddddddddddd");
		}
	}

}


