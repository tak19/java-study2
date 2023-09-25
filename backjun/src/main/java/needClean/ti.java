package needClean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class ti {
    public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int c = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][2];
		//자르는 거 입력받기
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//가로 세로 구분과 좌표 입력
			arr[i][0] = a;
			arr[i][1] = b;
		}
		//정렬 수행
		Arrays.sort(arr, (o1,o2) ->{
			return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
		});
		
		int maxR = 0;
		int maxC = 0;
		int slideR = 0;
		int slideC = 0;
		int nowRSlide = 0;
		int nowCSlide = 0;
		for(int i = 0 ; i < n ; i++) {
			//가로로 짜르는 거라면
			if( arr[i][0] == 0 ) {
				slideR = arr[i][1];
				int nowR = slideR - nowRSlide;
				if( nowR > maxR ) {
					maxR = nowR;
				}
				nowRSlide = slideR;
				
			}else {
				//세로로 짜르는 거라면
				slideC = arr[i][1];
				int nowC = slideC - nowCSlide;
				if( nowC > maxC ) {
					maxC = nowC;
				}
				nowCSlide = slideC;
				
			}
		}
		//System.out.println(nowRSlide);
		if( maxR < r - nowRSlide ) {
			maxR = r - nowRSlide;
		}
		if( maxC < c - nowCSlide ) {
			maxC = c - nowCSlide;
		}
		//System.out.println(maxR + " " + maxC);
		System.out.println(maxR * maxC);
		
	}
}
    