package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_09 {
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int[] dp = new int[n+1];
        int[] arr = new int[n+1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        //수열을 입력받음
        for(int i = 1 ; i < n+1 ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //각 자리 수가 자기 자신만 수열을 형성하는 경우로 1로 초기화
        for(int i = 1 ; i < n+1 ; i++){
            dp[i] = 1;
        }
        int re = 1; //결과 저장할꺼임
        
        for(int i=2; i < n+1; i++){
            for(int j=1; j < i; j++){
                //순서상 앞에 숫자가 작다면
                if( arr[i]  > arr[j] ){
                    dp[i] = Math.max(dp[j] + 1,dp[i]); //순열의 번호를 보고 길이 부여
                }
            }
            re = Math.max(re, dp[i]);
        }  
        System.out.println(re);

    }
}