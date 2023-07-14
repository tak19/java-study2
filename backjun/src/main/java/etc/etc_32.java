package etc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class etc_32 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int up = Integer.parseInt(st.nextToken());
		int down = Integer.parseInt(st.nextToken());
		int goal = Integer.parseInt(st.nextToken());
		
		//한번에 올라갈 수 있는 거리는 빼준다
		goal -= up;
		//gap만큼 올라가는데 차이가 난다면 +1회
		int gap = up - down;
		int result = goal / gap;
		if( goal % gap != 0 ) {
			result++;
		}
		
		System.out.println(++result);
		
		
	}
}