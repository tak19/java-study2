package competition;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class PIMM_B {
	static StringBuilder sb = new StringBuilder();
	static int N,M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		//전체 수열과 친구 수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//대기번호 입력 - 친구 수만큼만 입력 받으면된다.
		st = new StringTokenizer(br.readLine());
		ArrayList<Integer> list =  new ArrayList<>();
		for(int i = 0 ; i < M ; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		int result = 0;
		//친구 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			int friend = Integer.parseInt(st.nextToken());
			if( !list.contains(friend) ) {
				result++;
			}
		}
		System.out.println(result);
	}
}