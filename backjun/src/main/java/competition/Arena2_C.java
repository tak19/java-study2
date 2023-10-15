package competition;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Arena2_C {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		//배열 입력받음
		int[] arr = new int[n];
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int maxIndex = n-1;
		int min = Integer.MAX_VALUE;
		int minValue = arr[0];
		int maxValue = arr[maxIndex];
		
		min = maxValue - minValue;
		n += 2;
		while(n --> 0) {
			minValue *= 2;
			if( arr[0] > arr[1] ) {
				//정렬 후 값 비교
				Arrays.sort(arr);
			}
			if( min >= arr[maxIndex] - arr[0] ) {
				min = arr[maxIndex] - arr[0];
			}
		}
		System.out.println(min);
	}
}