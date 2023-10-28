package bronze;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class bronze_10 {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 입력
        final int towerHeight = Integer.parseInt(br.readLine());
        // 2의 제곱
        System.out.print(BigInteger.valueOf(1).shiftLeft(towerHeight));
    }
}