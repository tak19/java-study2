package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


public class datastructure_07 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int test = Integer.parseInt(st.nextToken());
		String[] arr = new String[N+1];
		
		HashMap<String,Integer> tMap = new HashMap<>();
		//N만큼 반복
		for(int i = 1 ; i <= N ; i++) {
			//해당 포켓몬의 순서를 값으로 가지는 Tmap만든다
			String s = br.readLine();
			arr[i] = s;
			tMap.put(s, i);
		}
		
		StringBuilder sb = new StringBuilder();
		
		//검증 시작
		for(int i = 0 ; i < test; i++) {
			
			String t = br.readLine();
			//숫자라면
			if( t.matches("[0-9]+") ) {
				int tem = Integer.parseInt(t);
				sb.append(arr[tem]).append("\n");
				//sb.append(b)
			}else {
				//문자열이라면 해당 키값의 값을 꺼내와 순서를 출력
				sb.append(tMap.get(t)).append("\n");
			}
			
		}
		System.out.println(sb);
		
	}
}