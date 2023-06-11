package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class datastructure_16 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		HashMap<Integer,Integer> hMap = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i++) {
			hMap.put(Integer.parseInt(st.nextToken()), 1);
		}
		StringBuilder sb = new StringBuilder();
		//key값을 가지고 방문
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < m ; i++) {
			int key = Integer.parseInt(st.nextToken());
			sb.append(hMap.getOrDefault(key, 0)).append(" ");
		}

		System.out.println(sb);
	}
}