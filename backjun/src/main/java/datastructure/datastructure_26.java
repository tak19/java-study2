package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class datastructure_26 {
	static StringBuilder sb = new StringBuilder();
	static long[] tree;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//수열 길이와 변동 횟수
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		//선언할 수열 길이 측정하기
		int treeH = 0;
		int len = N;
		while( len != 0 ) {
			len /= 2;
			treeH++;
		}
		int size = (int) Math.pow(2, treeH+1);
		int startIndex = (size / 2) - 1;
		//배열 선언 후 시작 인덱스 부터 값 넣어주기
		tree = new long[size+1];
		for(int i = 0 ; i < tree.length ; i++) {
			tree[i] = Long.MAX_VALUE;
		}
		for(int i = startIndex + 1 ; i <= startIndex + N ; i++) {
			tree[i] = Long.parseLong(br.readLine());
		}
		setTree(size-1);
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			s += startIndex;
			e += startIndex;
			sb.append(getMin(s,e)).append("\n");
		}
		System.out.println(sb);
		
	}
	private static long getMin(int s, int e) {
		long min = Long.MAX_VALUE;
		while( s <= e ) {
			if( s % 2 == 1 ) {
				min = Math.min(min, tree[s]);
				s++;
			}
			if( e % 2 == 0 ) {
				min = Math.min(min, tree[e]);
				e--;
			}
			s /= 2;
			e /= 2;
		}
		return min;
	}
	private static void setTree(int index) {
		while( index != 1 ) {
			if( tree[index/2] > tree[index] ) {
				tree[index/2] = tree[index];
			}
			index--;
		}
	}
}