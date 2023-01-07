package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class greedy7 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for(int test =0; test < t ; test++) {
			int n = sc.nextInt();
			int[] arr = new int[n];
			int[] miny = new int[n];
			int pi = n - 1;
			
			for(int i = 0 ; i < arr.length; i++) {
				arr[i] = sc.nextInt();
			}
			//통나무 정렬
			Arrays.sort(arr);
			int harp = pi / 2; //배열의 중앙을 파악
			miny[harp] = arr[pi]; //가장 큰 기둥을 중앙에 배치
			
			int cnt = 1;
			int point = 1;
			for(int i = 0 ; i < harp ; i++) {
				miny[harp-cnt] =arr[pi - point];
				miny[harp+cnt] =arr[pi - (point + 1)];
				cnt++;
				point += 2;
			}
			//배열의 길이가 짝수 인 경우 마지막 원소(최소값)가 안들어감 -> 수동입력 
			if(arr.length % 2 == 0) {
				miny[pi] = arr[0];
			}
			
			int sub = 0;
			
			//인접 통나무간의 차 중 최대값 계산
			for(int i = 0 ; i < pi  ; i++) {
				sub = Math.max(sub, Math.abs(miny[i+1] - miny[i]));
			}
			//마지막 통나무와 첫번째 통나무 간의 차 계산
			sub = Math.max(sub, Math.abs(miny[pi]-miny[0]));
			
			System.out.println(sub);
			
		}
		
	}

}
