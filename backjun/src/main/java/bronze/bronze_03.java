package bronze;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class bronze_03 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char hangul = br.readLine().charAt(0);

		System.out.println((int) hangul - 44031); // '가'라고 찍으면 44032가 나옴. '가'가 1이되려면 44031 빼주기
	}
}