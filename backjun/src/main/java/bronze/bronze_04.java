package bronze;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bronze_04 {
	public static void main(String[] args) throws Exception{
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                StringBuilder sb = new StringBuilder(st.nextToken());
                System.out.println(sb.reverse());
            }
        }

        br.close();
	}
}
