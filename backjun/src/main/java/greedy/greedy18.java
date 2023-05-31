package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class greedy18 {
	static boolean[] eat;
	static int n,len;
	static char[] c;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		len = Integer.parseInt(st.nextToken());
		
		c = br.readLine().toCharArray();
		eat = new boolean[n];
		int result = 0;
		for(int i = 0 ; i < n ; i++) {
			//사람이 나온다면
			if( c[i] == 'P' ) {
				//왼쪽부터 먹어.. 자신의 위치보다 먼쪽부터
				if( canEat(i) ) {
					result++;
				}
			}
		}
		System.out.println(result);
		
	}
	//먹을 수 있는 햄버거가 있다면 해당 햄버거 먹은 표시하고 true반환
	private static boolean canEat(int index) {
		int start = Math.max(index-len, 0);
		int end = Math.min(index + len, n-1);
		for( int i = start ; i <= end ; i++ ) {
			if( c[i] == 'H' && !eat[i] ) {
				eat[i] = true;
				return true;
			}
		}
		return false;
	}
}