package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class datastructure_19 {
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//제한 인원과 신청 횟수 입력
		N = Integer.parseInt(st.nextToken());
		int student = Integer.parseInt(st.nextToken());
		
		Map<String,Integer> linkMap = new LinkedHashMap<>();
		//학생 입력받기
		for(int i = 0 ; i < student; i++) {
			String stuNo = br.readLine();
			// 값이 없다면 넣고, 있다면 뒤로 밀음
			if( !linkMap.containsKey(stuNo) ) {
				linkMap.put(stuNo, 1);
			}else {
				linkMap.remove(stuNo);
				linkMap.put(stuNo, 1);
			}
		}
		Set<String> keys = linkMap.keySet();
		StringBuilder sb = new StringBuilder();
		
		int cnt = 0;
		for( String key : keys ) {
			cnt++;
			sb.append(key).append("\n");
			if( cnt == N ) {
				break;
			}
		}
		System.out.println(sb);
	}
}