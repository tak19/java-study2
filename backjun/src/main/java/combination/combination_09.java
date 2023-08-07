package combination;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class combination_09 {
	static int N;
	static boolean[] select;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//순열 N을 입력받는다.
		N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		//순열을 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); 
		}
		
		if( np(arr) ) {
			for( int i : arr ) {
				sb.append(i).append(" ");
			}
		}else {
			sb.append("-1");
		}
		
		System.out.println(sb);
		
	}
	private static boolean np(int[] arr) {
		int i = arr.length -1;
		int j = arr.length -1;
		int k = arr.length -1;
		 //1. a[i-1] > a[i]를 만족하는 첫 번째 수 찾기
        while (i > 0 && arr[i-1] <= arr[i]) {
            i--;
        }
        // 첫 번째 순열인 경우
        if (i <= 0) {
            return false;
        }
        
       //2. a[i-1] > a[j]를 만족하는 첫 번째 수 찾기
        while (arr[j] >= arr[i-1]) {
            j--;
        }
        swap(arr, i-1, j);

        //4 i부터 a.length-1까지 순열 뒤집기
        while (i < k) {
            swap(arr, i++, k--);
        }
        return true;
	}
	//스왑
	private static void swap(int[] arr, int i, int j) {
	       int temp = arr[i];
	        arr[i] = arr[j];
	        arr[j] = temp;
	}
}