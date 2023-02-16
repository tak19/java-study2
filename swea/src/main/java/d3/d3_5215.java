package d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.AllPermission;
import java.util.StringTokenizer;

public class d3_5215 {
	private static StringBuilder sb = new StringBuilder();
	static int n,limitCal;
	static int result,sumPoint;
	static int[] point;
	static int[] kcal;
	static boolean[] isSelect;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1 ; test_case <= T ; test_case++) {
			sb.append("#" + test_case + " ");

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			limitCal = Integer.parseInt(st.nextToken());

			point = new int[n];
			kcal = new int[n];

			for(int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(br.readLine());
				point[i] = Integer.parseInt(st.nextToken());
				kcal[i] = Integer.parseInt(st.nextToken());
			}

			isSelect = new boolean[n];
			result = 0;


			subSet(0,0);
			sb.append(result).append("\n");
		}

		System.out.println(sb);

	}

	private static void subSet(int cnt, int sum) {
		if( sum <= limitCal) {
			if( cnt == n ) {
				result = Math.max(result, sumPoint);
				return;
			}

			isSelect[cnt] = true;
			sumPoint+= point[cnt];
			subSet(cnt+1, sum + kcal[cnt]);
			isSelect[cnt] = false;
			sumPoint-= point[cnt];
			subSet(cnt+1, sum );
		}else {
			return;
		}





	}
}



