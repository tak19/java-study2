package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sort_07 {
	public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n*n];
        StringTokenizer st;
        int cnt= 0;
        for(int i = 0; i < n ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n ; j++){
                arr[cnt++] = Integer.parseInt(st.nextToken()); 
            }
        }
        Arrays.sort(arr);
        System.out.println(arr[arr.length - n]);


    }
}