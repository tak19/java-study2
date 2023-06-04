package lecture;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class lecture_01 {
	static boolean[] select;
	static int cnt,visit,total;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		total = ( 1<<10 ) - 1; //최종으로 다 뽑혔을 경우 - 10자리
		//TC실행
		for(int test = 1 ; test <= T ; test++) {
			sb.append("#").append(test).append(" ");
			int n = Integer.parseInt(br.readLine());
			int tem = n;
			int result = 0;
			visit = 0;
			while( !ck(tem) ) {
				result++;
				tem = n*result;
			}
			sb.append(n*result).append("\n");
		}
		System.out.println(sb);
	}
	//방문 배열 체크
	private static boolean ck(int n) {
		while( n > 0 ) {
			int tem = n % 10;
			visit = visit | ( 1 << tem );
			n /= 10;
		}
		if( visit == total ) {
			return true;
		}
		
		return false;
	}
}