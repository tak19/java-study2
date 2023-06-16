package simul;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class simul_18 {
	static int[] arr;
	static boolean[] visit;
	static int n;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		visit = new boolean[n];
		//풍선 배열 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		boom(0,0);
		//System.out.println(Arrays.toString(visit));
		System.out.println(sb);
		
	}
	//터트림
	private static void boom(int index, int cnt) {
		
		sb.append( index + 1 ).append(" ");
		if( cnt == n-1 ) {
			return;
		}
		visit[index] = true;
		int plus = arr[index];
		int nextIndex = 0;
		//오른쪽 이동
		if( plus > 0 ) {
			nextIndex = rigthMove(index,plus);
		}else {
			//왼쪽이동
			nextIndex = leftMove(index,plus);
		}
		if( nextIndex == -1 ) {
			return;
		}
		boom(nextIndex,cnt+1);
	}
	
	//오른쪽 이동
	private static int rigthMove(int index, int plus) {
		int nowIndex = index;
		int cnt = 0;
		while(cnt <= plus) {
			nowIndex = (nowIndex + 1) % n;
			//System.out.println("nowIndex: " + nowIndex);
			if( !visit[nowIndex] ) {
				//System.out.println("방문함 -> " + nowIndex);
				cnt++;
			}
			if( cnt == plus ) {
				return nowIndex;
			}
		}
		return -1;
	}
	
	//왼쪽 이동
	private static int leftMove(int index, int plus) {
		//System.out.println("왼쪽 이동: " + index + " " + plus);
		int nowIndex = index;
		int cnt = 0;
		while(cnt >= plus) {
			nowIndex = ((nowIndex - 1) % n);
			if( nowIndex < 0 ) {
				nowIndex += n;
			}
			//System.out.println("nowIndex: " + nowIndex);
			if( !visit[nowIndex] ) {
				cnt--;
			}
			if( cnt == plus ) {
				return nowIndex;
			}
		}
		return -1;
	}	
}