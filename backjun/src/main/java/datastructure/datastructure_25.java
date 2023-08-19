package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class datastructure_25 {
	static int N,M,K;
	static long[] tree;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//데이터 수, 변환수, 구간합 횟수 입력받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//트리 높이 크기 계산하기
		int height = 0;
		int len = N;
		while( len != 0 ) {
			len /= 2;
			height++;
		}
		int size = (int) Math.pow(2, height+1);
		int leftStartIndex = size / 2 -1;
		tree = new long[size+1];
		
		//인덱스에 맞게 값 입력받기
		for(int i = leftStartIndex+1 ; i <= leftStartIndex + N ; i++) {
			tree[i] = Long.parseLong(br.readLine());
		}
		//트리 생성
		setTree(size-1);
		
		for(int i = 0 ; i < M + K ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			long e = Long.parseLong(st.nextToken());
			//값 변경
			if( a == 1 ) {
				changeVal(leftStartIndex + s, e);
			}else {
				//구간합 구하기
				s = s + leftStartIndex;
				e = e + leftStartIndex;
				sb.append(getSum(s,(int)e)).append("\n");
			}
			
		}
		System.out.println(sb);
		
	}
	//구간합
	private static long getSum(int s, int e) {
		long partSum = 0;
		while( s <= e ) {
			if( s % 2 == 1 ) {
				partSum += tree[s];
				s++;
			}
			if( e % 2 == 0 ) {
				partSum += tree[e];
				e--;
			}
			s /= 2;
			e /= 2;
		}
		return partSum;
	}
	//값 변경
	private static void changeVal(int index, long val) {
		long diff = val - tree[index];
		while(index > 0) {
			tree[index] = tree[index] + diff;
			index = index / 2;
		}
	}
	//트리 만들기
	private static void setTree(int i) {
		while( i != 1 ) {
			tree[i/2] += tree[i];
			i--;
		}
	}
}