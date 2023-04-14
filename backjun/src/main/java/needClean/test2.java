package needClean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * A,B 문 둘중에 하나씩 파악하면됨
 * 둘중 나가는 값이 큰값이 정답 가능성이 있는 값이다
 */
public class test2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		int[] arr = new int[201];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < T ; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[num+100]++;
		}
		int goal = Integer.parseInt(br.readLine());
		System.out.println(arr[goal+100]);
		
	}
}