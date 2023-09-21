package string;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class string_07 {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		String s = br.readLine();
		Set<String> set = new HashSet<>();
		set.add(s);
		//부분집합들을 넣는다
		for(int i = 0 ; i < s.length() ; i++) {
			set.add(s.substring(i,i+1));
			for(int j = i+1 ; j <= s.length() ; j++) {
				set.add(s.substring(i,j));
			}
		}
		System.out.println(set.size());
		
	}
}