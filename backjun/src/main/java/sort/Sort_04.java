package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sort_04 {
	static int[] arr,tem;
	static long result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		arr = new int[n+1];
		tem = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		result = 0; //결과값 저장함
		//병합정렬 시작 -- 시작 위치랑 끝위치 인덱스 정보를 가지고 병합
		mergeSort(1,n);
		
		System.out.println(result);
		
	}
	private static void mergeSort(int s, int e) {
		if( e - s < 1) { //원소 1개 이하 되면 종료 -- 2개부터 정렬함
			return;
		}
		
		int mid = s + (e - s) / 2; //중간값
		mergeSort(s,mid); // 처음 ~ 중간
		mergeSort(mid+1,e); //중간 ~ 끝

		for(int i = s ; i <= e ; i++) {
			tem[i] = arr[i];
		}
		
		int k = s;
		int index1 = s;
		int index2 = mid+1;
		
		while( index1 <= mid && index2 <= e ) {
			// 양쪽 비교하면서 병합 실시 -- 투포인터 활용
			// index 2가 더 큰경우 그대로 진행함 -- 버블 Sort상 swap없음
			if( tem[index1] <= tem[index2] ) { //크거나 같으면 버블Sort상 수의 변경이 안일어난다!! 
				arr[k] = tem[index1];
				k++;
				index1++;
			}else if( tem[index1] > tem[index2] ) { //index2 가 더 작은 경우로 버블 Sort상에서 swap 발생
				//result += e - k ;
				result += index2 - k ; // 해당 원소의 인덱스 값에서 현재 대입할 인덱스값을 빼준다. --> 이동횟수 
				//result += 1 ;
				arr[k] = tem[index2];
				k++;
				index2++;
			}
		}
		//남은 원소 배열 뒤에 채우기
		while( index1 <= mid ) {
			arr[k] = tem[index1];
			k++;
			index1++;
		}
		while( index2 <= e ) {
			arr[k] = tem[index2];
			k++;
			index2++;
		}

	}
}