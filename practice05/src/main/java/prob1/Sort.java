package prob1;

public class Sort {
	
	public static void main(String[] arg) {
	
		int array[] = { 5, 9, 8, 8, 60, 20, 1 };
		//배열 크기
		int count =  array.length;
		
		System.out.println( "Before sort." );
		
		for (int i = 0; i < count; i++) {
			System.out.print( array[ i ] + " " );
		}
		
		
		
		//
		// 정렬 알고리즘이 적용된 코드를 여기에 작성합니다.
		for (int i = 0; i < count-1; i++) {
			for(int j = 1 ; j < count ; j++) {
				//앞에 수가 더 크다면... 앞으로 와야함
				if(array[j-1] < array[j]) {
					int max = array[j];
					array[j] = array[j-1]; 
					array[j-1] = max;
				}else {
					continue;
				}
			}
			
		}
		
		
		//

		
		// 결과 출력
		System.out.println( "\nAfter Sort." );
		
		for (int i = 0; i < count; i++) {
			System.out.print(array[i] + " ");
		}		
	}
}