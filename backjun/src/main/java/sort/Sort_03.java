package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Sort_03 {
	static int[] arr,tem;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		arr = new int[n+1];
		tem = new int[n+1]; // 변환 배열을 저장하기 위함
		for(int i = 1 ; i <= n ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		mergeSort(1,n);
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n ; i++) {
			sb.append(arr[i] + "\n");
		}
		System.out.println(sb);
	}

	//병합 정렬 수행 -- s: 시작점 e: 종료점
	private static void mergeSort(int s, int e) {
		//끝점 - 시작점: 1보다 작으면 원소개수가 한개로 맞춰짐
		if( e - s < 1 ) {
			return;
		}
		//중간값 계산 -> 반으로 가르기
		int m =  ( e + s ) / 2;
		//반을 기준으로 다시 재귀 호출함
		mergeSort(s, m);  //시작 ~ m
		mergeSort(m+1, e); // m+1 ~ 끝
		
		// 시작에서 중간이든 중간에서 시작이든 여하튼 시작은 's'이고 끝은 'e'임
		for(int i = s ; i <= e; i++) {
			tem[i] = arr[i]; //해당 범위의 배열을 옮긴 후 정렬 --> 원본 저장 위해
		}
		//병합 시작
		int k = s;
		int index1 = s; //앞쪽 시작점
		int index2 = m+1; //뒤쪽 시작점
		//그룹 병합 -> 양쪽 그룹의 index가 가리키는 값 비교 후 더 작은 수를 선택해 배열에 저장 후 index 이동
		while( index1 <= m && index2 <= e ) {
			if(tem[index1] > tem[index2] ) {
				arr[k] = tem[index2];
				k++;
				index2++;
			}else {
				arr[k] = tem[index1];
				k++;
				index1++;
			}
		}
		//남아 있을 수도 있는 배열 값들은 뒤에 붙여준다.
		while( index1 <= m) {
			arr[k] = tem[index1];
			k++;
			index1++;
		}
		while( index2 <= e) {
			arr[k] = tem[index2];
			k++;
			index2++;
		}
		
	}
}
