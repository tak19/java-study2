package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class datastructure_15 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		TreeMap<String,String> tMap = new TreeMap<>( (o1,o2) -> {
			return o2.compareTo(o1);
		});
		
		// 파일 입력받기
		String name = "";
		String log = "";
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			name = st.nextToken();
			log = st.nextToken();
			//해당 확장자의 값이 없다면 0을 반환 있다면 해당 value를 반환함
			tMap.put( name , log );
		}
		StringBuilder sb = new StringBuilder();
		//key값을 가지고 방문
		Set<String> ketSet = tMap.keySet();
		for(String key : ketSet) {
			if(tMap.get(key).equals("enter")) {
				sb.append(key).append("\n");
			}
		}

		System.out.println(sb);
	}
}