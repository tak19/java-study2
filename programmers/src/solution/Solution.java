package solution;

public class Solution {
	static String[] arr;
	static String tem;
	static int cacheSize;
	public static void main(String[] args) {
		cacheSize = 5;
		String[] c = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
		int result = solution(cacheSize, c);
		System.out.println(result);
	}
	static public int solution(int cacheSize, String[] cities) {
		int answer = 0;

		arr = new String[100001];
		int lastIndex = 0;
		for(int i = 0 ; i < cities.length; i++){
			System.out.println(i+1 + " 번째실행");
			tem = cities[i].toUpperCase();
			//캐쉬에 있는지확인 - 마지막 인덱스 위치와 캐쉬 사이즈 넘김
			if( ck(lastIndex,cacheSize)){

				answer = answer + 1;
				System.out.println("일치함 -- " + tem);
				arr[lastIndex-1] = tem;
			}else{
				//없는 캐쉬일때만 인덱스를 뒤로 밀어줌
				answer = answer + 5;
				System.out.println("불일치: " + tem);
				arr[lastIndex++] = tem;
			}
			print(i,lastIndex);
			System.out.println();

		}


		return answer;
	}

	//캐쉬에 값이 있는지확인
	static boolean ck(int lastIndex, int size){

		int len = lastIndex-size > 0 ? (lastIndex-size) : 0;
		//해당 값이 있다면
		for(int i = lastIndex ; i >= len ; i--){
			if( tem.equals(arr[i]) ){
				//System.out.println(tem);
				push(lastIndex,i);
				return true;
			}
		}
		return false;
	}
	static void push(int lastIndex, int to){
		String s = arr[to];
		for(int i = to ; i < lastIndex ; i++){
			arr[i] = arr[i+1];
		}
		//arr[lastIndex] = s;
	}
	
	private static void print(int cnt, int lastIndex) {
		System.out.println();
		System.out.println("------- 값: " + arr[cnt] );
		int len = lastIndex-cacheSize > 0 ? (lastIndex-cacheSize) : 0; 
		System.out.println("lastIndex: " + lastIndex + " len: " + len);
		for(int i = lastIndex -1 ; i >= len; i--) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		
	}


}