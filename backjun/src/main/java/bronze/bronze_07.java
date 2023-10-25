package bronze;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bronze_07 {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String s = br.readLine();
            if (s.equals("END")) break;
            StringBuilder reverse = new StringBuilder(s);
            reverse = reverse.reverse();
            sb.append(reverse + "\n");
        }
        System.out.print(sb);
    }
}