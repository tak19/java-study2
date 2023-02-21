package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
//게리맨더링
public class etc_02 {
	private static StringBuilder sb = new StringBuilder();
	static boolean isSelect;
	static int n;
	static int[] region;
	static List[] list;

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); //지역구 수
		
		//지역구 인원 수 입력받음
		region = new int[n];
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i++) {
			region[i] = Integer.parseInt(st.nextToken());
		}
		list = new ArrayList[];
		
		
		System.out.println(sb);


	}

}
