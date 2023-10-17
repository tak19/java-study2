package bronze;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class bronze_01 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int firstSec = Integer.parseInt(br.readLine());
		int SecondSec = Integer.parseInt(br.readLine());
		int ThirdSec = Integer.parseInt(br.readLine());
		int FourthSec = Integer.parseInt(br.readLine());

		int sum = (firstSec + SecondSec + ThirdSec + FourthSec);
		int resultMin = sum / 60;
		int resultSec = sum - (resultMin * 60);

		System.out.println(resultMin);
		System.out.println(resultSec);
	}
}
