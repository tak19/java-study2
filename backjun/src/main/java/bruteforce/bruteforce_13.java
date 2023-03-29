package bruteforce;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bruteforce_13 {
	static int N,result;
	static int[] input;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		//연결정보 입력받음
		map = new int[N][N];
		StringTokenizer st = null;
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		input = new int[N];
		//방문도시 초기화진행
		for(int i = 0 ; i < N ; i++) {
			input[i] = i;
		}
		//최소값 저장을 위해 Integer 최대값으로 저장
		result = Integer.MAX_VALUE;
		do {
			int cost = 0;
			int money = 0;
			boolean impossible = false;
			for(int i = 1 ; i < N ; i++) {
				int start = input[i-1];
				int end = input[i];
				money = map[start][end];
				//설정한 경로에 갈 수 없는 경우가 포함되어 있다면 종료!!
				if( money == 0 ) {
					impossible = true;
					break;
				}
				cost += money;
			}
			money = map[input[N-1]][input[0]];
			// 마지막 도시에서 시작지점으로 가는곳도 연결되어 있는지 확인해야함
			if( money != 0 && !impossible ) {
				cost += money;
				result = Math.min(result, cost);
			}
			
		}while( np( input ));
		System.out.println(result);
	}
	//NP
	private static boolean np(int[] input) {
		int n = input.length;
		
		int i = n-1;
		while( i > 0 && input[i] < input[i-1] ) {
			i--;
		}
		if( i == 0 ) {
			return false;
		}
		
		int j = n-1;
		while( input[j] < input[i-1] ) {
			j--;
		}
		
		swap(i-1,j);
		
		int k = n-1;
		while( i < k ) {
			swap(i++, k--);
		}
		
		return true;
	}
	//자리 스왑
	private static void swap(int i, int j) {
		int tem = input[i];
		input[i] = input[j];
		input[j] = tem;
	}
}