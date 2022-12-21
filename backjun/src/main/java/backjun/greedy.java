package backjun;

import java.util.Scanner;
import java.util.Stack;

public class greedy {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();

		int sum=0;
		int total = 0;
		String[] arr = s.split("-");
		
//		System.out.println(arr[0]);
		
		//첫 - 만나기전의 숫자를 +로 구분하여 각 숫자를 더해줌
		//+는 특수문자기 때문에 []로 감싸거나 \\ 필요
		String[] first = arr[0].split("[+]");
		//System.out.println(first.length);
		//처음 - 만나기 전까지 숫자는 더해줌
		for(int i = 0 ; i < first.length; i++) {
			total += Integer.parseInt(first[i]); 
		}
		
		//2번째 - 부터 그전 -까지 합을 구해서 total에 계속 빼줌
		for(int i = 1 ; i < arr.length; i++) {
			sum=0;
			String[] hap = arr[i].split("[+]");
			for(int j = 0 ; j < hap.length; j++) {
				sum += Integer.parseInt(hap[j]); 
			}
			total -= sum;
			
		}
		System.out.println(total);
		//입력받은 문자열 char 배열로 변환
		//char[] c = sc.nextLine().toCharArray();
		
		
		
		
	}

}
