package datastructure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class datastructure_17 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		TreeMap<String,Integer> tMap = new TreeMap<>();
		
		String key = "";
		for(int i = 0 ; i < n ; i++) {
			key = br.readLine();
			tMap.put( key , tMap.getOrDefault( key , 0) + 1 );
		}
		//StringBuilder sb = new StringBuilder();
		String result ="";
		int maxCnt = 0;
		//key값을 가지고 방문
		Set<String> keySet = tMap.keySet();
		for( String k : keySet ) {
			if( maxCnt < tMap.get(k) ) {
				maxCnt = tMap.get(k);
				result = k;
			}
		}

		System.out.println(result);
	}
}