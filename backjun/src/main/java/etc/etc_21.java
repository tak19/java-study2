package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class etc_21 {
	static int[][] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		for(int i = 0 ; i < n ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < n ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(div(0,0,n));
		
		
		
	}
	
	private static int div(int i, int j, int size) {
		//2로 다 분할 했음
		if( size == 2) {
			
			int[] second = new int[4];
			int index = 0;
			
			for(int x = i ; x < i+2 ; x++) {
				for( int y = j ; y < j+2 ; y++) {
					second[index++] = arr[x][y];
				}
			}
			Arrays.sort(second);
			//오름차순 정렬이기 때문에 인덱스 2 반환
			return second[2];
		}else { //아니면 자름
			int[] result = new int[4];
			int newSize = size/2;
			
			result[0] = div(i , j , newSize);
			result[1] = div(i + newSize , j , newSize);
			result[2] = div(i , j + newSize , newSize);
			result[3] = div(i + newSize , j + newSize , newSize);
			
			Arrays.sort(result);
			return result[2];
			
			
		}
		
		
		
	}
}
