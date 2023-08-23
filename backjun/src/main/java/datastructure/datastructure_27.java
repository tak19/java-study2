package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class datastructure_27 {
	static StringBuilder sb = new StringBuilder();
	static long[] tree;
	static int MID = 1_000_000_007;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//수열 길이, 숫자 변경 구함 수 입력
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		//트리 길이 계산하기
		int size = 0;
		int len = n; 
		while( len != 0 ) {
			len /= 2;
			size++;
		}
		size = (int) Math.pow(2, size+1);
		int startIndex = (size / 2) - 1;
		tree = new long[size+1];
		//곱셈을 위해 1로 초기화
		for(int i = 0 ; i <= size ; i++) {
			tree[i] = 1;
		}
		//수열 입력받기
		for(int i = startIndex+1 ; i <= startIndex + n ; i++) {
			tree[i] = Long.parseLong(br.readLine());
		}
		setTree(size-1);
		//연산 시작
		for(int i = 0 ; i < m + k ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			//상황에 따른 동작
			if( a == 1 ) {
				changeVal(startIndex + b, c);
			}else {
				b += startIndex;
				c += startIndex;
				sb.append(getMul(b,c)).append("\n");
			}
		}
		System.out.println(sb);
		
	}
	//값 더하기
	private static Long getMul(int b, int c) {
		long sumMul = 1;
		while( b <= c ) {
			if( b % 2 == 1 ) {
				sumMul = (sumMul * tree[b]) % MID;
				b++;
			}
			if( c % 2 == 0 ) {
				sumMul = (sumMul * tree[c]) % MID;
				c--;
			}
			b /= 2;
			c /= 2;
		}
		return sumMul;
	}
	//값 바꾸기 - b번째 수를 c로 바꾸기
	private static void changeVal(int index, int val) {
		tree[index] = val;
		while( index > 1 ) {
			index /= 2;
			tree[index] = ( (tree[index*2] % MID) * (tree[index*2 +1] % MID)) % MID;
		}
	}
	//초기 트리 값 세팅
	private static void setTree(int startIndex) {
		while( startIndex != 1 ) {
			tree[startIndex/2] = (tree[startIndex/2] * tree[startIndex]) % 1_000_000_007;
			startIndex--;
		}
	}
}
