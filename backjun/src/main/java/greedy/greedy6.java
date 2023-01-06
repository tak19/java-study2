package greedy;

import java.util.Scanner;

public class greedy6 {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		long n = sc.nextLong();
		long min = 51; //50이하의 수들만 주어지기 때문에 51로 선언
		long max = 0;
		//주사위 배열 생성후 값 입력
		long[] arr = new long[6];
		for(int i = 0 ; i < 6 ; i++) {
			arr[i] = sc.nextInt();
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}
		sc.close();
		
		//n이 1일경우 예외처리 - 가장 큰수만 가리면 됨
		if( n == 1) {
			int five = 0;
			for(int i = 0 ; i < 6 ; i++) {
				five += arr[i];
			}
			five -= max;
			System.out.println(five);
			return;
		}
		
		long total = 0 ;
		//3면이 노출되기 전까지.. 즉, (n-1)층 까지가 제일 작은 수 한면만 노출하는 경우의 수
		//마지막 위에층에 테두리를 제외한 부분은 최소수가 보이는곳임 -- 2(n-2)
		total = min * (4*(n-1)*(n-2) + (n-2) *(n-2)); //한면만 노출되는 주사위 수.. 해당 면이 (n-1)번까지 반복됨
		
		
		
		//두면 노출하는 주사위 갯수 = 4 *(n-1) + 4 * (n-2)
		long minT = 101; //두 주사위 면 합중에 가장 작은 값을 저장할꺼임
		for(int i = 0 ; i < 6 ; i++) {
			int no = 5 - i;
			for(int j = 0 ; j < 6 ; j++) {
				if( j == no  || i==j ) {
					//서로 마주보는 면이면 제외 or 자기자신
					continue;
				}
				//두면을 더하고 두면의 합중 최소값 얻음
				minT = Math.min(minT,arr[i] + arr[j]);  
			}
		}
		//System.out.println((4 *(n-1) + 4 * (n-2)) * minT);
		total +=  (4 *(n-1) + 4 * (n-2)) * minT;
		
		
		
		//total += minT;
		//세면 노출하는 주사위 갯수 = 맨위층 끝 4개 -- 4개 ||| 마주보는 면의 인덱스 => 5 - index = 마주보는 면
		//단, 세면을 노출하는 주사위는 마주보는 면이랑 겹치는 수가 같음.. 따라서 둘중 최소값으로 계산??
		//아니 남은 두면이 마주보지만 않으면 해결됨!!!!
		/*
		for(int i = 0 ; i < 6 ; i++) {
			int no = 5 - i;
			for(int j = 0 ; j < 6 ; j++) {
				if(j == no || j == i) { //마주보는 면이거나 자신의 면을 제외하고 탐색
					continue;
				}
				
				
				for(int x = 0 ; x < 6 ; x ++) {	//나머지 면이 나옴
					if( x == i || x == no || x == j || j+x == 5) { //인접한면과 인접면이 기준or 기준 반대면이면 제외or 해당면이 인접면인 경우or 비교면과 반대인경우
						continue;
					}
					three = Math.max(three, arr[i]+arr[j]+arr[x]);
				}
				
			}
		}
		total += 4 * three;
		*/
		
		long min1 = Math.min(arr[0], arr[5]);
		long min2 = Math.min(arr[1], arr[4]);
		long min3 = Math.min(arr[2], arr[3]);
		long three = min1 + min2 +min3;
		total += three *4;
		System.out.println(total);
		
	}

}
