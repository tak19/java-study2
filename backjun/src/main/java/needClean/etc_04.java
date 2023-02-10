package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_04 {
	static int n,m;
	//static int dx 
	
	
private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/17478_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int robotX = Integer.parseInt(st.nextToken()); 
		int robotY = Integer.parseInt(st.nextToken());
		int robotDir = Integer.parseInt(st.nextToken());
		
		System.out.println(sb);
		
		
	}
	
}

