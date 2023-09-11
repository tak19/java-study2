package binarysearch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class binary_08 {
	static StringBuilder sb = new StringBuilder();
	//static int N,M;
	//static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		String s = br.readLine();
		//날짜 구하기
		String[] arr = s.split("-");
		int year = Integer.parseInt(arr[0]);
		int month = Integer.parseInt(arr[1]);
		int day = Integer.parseInt(arr[2]);
		
		int n = Integer.parseInt(br.readLine());
		
		 // 결과값 저장
        int resultY = year;
        int resultM = month;
        int resultD = day + n;

        // 일(day)이 30을 넘으면 월(month)을 조정
        while (resultD > 30) {
            resultM++;
            resultD -= 30;
        }

        // 월(month)이 12를 넘으면 년(year)을 조정
        while (resultM > 12) {
            resultY++;
            resultM -= 12;
        }

        // 계산된 년(year), 월(month), 일(day)을 이용하여 yyyy-mm-dd 형태로 출력
        System.out.printf("%04d-%02d-%02d\n", resultY, resultM, resultD);
    }
}