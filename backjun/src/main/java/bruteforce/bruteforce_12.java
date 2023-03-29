package bruteforce;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 대각선방향으로 진행된다...
 * 두 x,y의 합으로 인덱스를 지정해서 List 배열에 저장한다.
 */
public class bruteforce_12 {
	static int N,range,result,ans;
	static int map[][];
	static List<int[]>[] listBishop;
	static int[] isSelect;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //체스판 크기

		//사선방향을 저장할 리스트 뽑음
		range = (2*N);
		isSelect = new int[2*N];

		listBishop = new ArrayList[range];
		for(int i = 0 ; i < range ; i++ ) {
			listBishop[i] = new ArrayList<>();
		}
		//체스판을 입력받아유~
		StringTokenizer st = null;
		map = new int[N][N];
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
		//행-뽑은 수-남은 수 전달
		positionBishop(0, 0);
		ans = result;
		//System.out.println(result);
		result=0;	
		positionBishop(1, 0);
		ans += result;
		//System.out.println(result);
		System.out.println(ans);

	}

	private static void positionBishop(int row, int cnt) {

		//		if( result >= ((cnt + (range+1-row))/2 )) {
		//			return;
		//		}

		//다 뽑았다면!!!
		if( row >= range ) {
			result = Math.max(result, cnt);
			return;
		}

		//뽑은적 없는 사선이라면!!
		for( int[] tem : listBishop[row] ) {
			int index = tem[0] - tem[1];
			index = index >= 0 ? index : index + (2*N);
			if( isSelect[index] == 0 ) {
				isSelect[index] = 1;
				positionBishop(row+2, cnt+1);
				isSelect[index] = 0;
			}

		}
		positionBishop(row+2, cnt);

	}

}

