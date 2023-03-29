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
	static int N,result,ans,total;
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
		ans += result;
		result=0;
		positionBishop(1, 0, 0);
		ans += result;

		System.out.println(ans);

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
			int index = Math.abs(tem[0] - tem[1]);
			if( isSelect[index] == 0 ) {
				isSelect[index] = 1;
				positionBishop(row+2, cnt+1, remain+1);
				isSelect[index] = 0;
			}
			
		}
		positionBishop(row+2, cnt, remain+1);

	}

}

