package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class datastructure_23 {
	static int N,M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map<String,String> hmap = new HashMap<>();
		String key = "";
		String pw = "";
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			key = st.nextToken();
			pw = st.nextToken();
			hmap.put(key, pw);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < M ; i++) {
			key = br.readLine();
			sb.append(hmap.get(key)).append("\n");
		}
		System.out.println(sb);
	}
}