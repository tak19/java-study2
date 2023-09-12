package competition;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dande_A {
	static StringBuilder sb = new StringBuilder();
	static int N,M;
	//static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		String s = br.readLine();
		
		int cnt = countOccurrences(s, "DKSH");
		System.out.println(cnt);
		
	}
    public static int countOccurrences(String input, String target) {
        int count = 0;
        int index = 0;
        
        while ((index = input.indexOf(target, index)) != -1) {
            count++;
            index += target.length();
        }
        
        return count;
    }
}