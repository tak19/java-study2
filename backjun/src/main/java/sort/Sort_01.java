package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sort_01 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		
		Pos[] intArr = new Pos[n];
		for( int i = 0; i < n ; i++) {
			intArr[i] = new Pos(Integer.parseInt(br.readLine()), i);
		}
		// 람다식 -- 양수면 자리 바꿔준다! 즉 오름차순 정렬
		Arrays.sort(intArr, (o1, o2) -> {
			return o1.data - o2.data;
		} );

		// 왼쪽으로는 한칸 밖에 못가기 때문에 
		// 왼쪽으로 가장 많이 간 원소를 찾으면됨!
		// 이전 인덱스  <  정렬 후 인덱스
		int max = 0;
		for( int i = 0; i < n ; i++) {
			if ( intArr[i].index  > i ) {
				max = Math.max(max, intArr[i].index -i );
			}
		}
		// 마지막 sort 연산까지해서 +1
		System.out.println(max+1);
		
	}
	static class Pos {
		int data;
		int index;
		Pos(int data,int index){
			this.data = data;
			this.index = index;
		}
		
		
	}
}
