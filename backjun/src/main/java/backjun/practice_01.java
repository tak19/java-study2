package backjun;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
문자열의 뒤에 A를 추가한다.
문자열을 뒤집고 뒤에 B를 추가한다.
 */
public class practice_01 {
	static int tem;
	static boolean[] select;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		sb.append("         ,r'\"7").append("\n");
		sb.append("r`-_   ,'  ,/").append("\n");
		sb.append(" \\. \". L_r'").append("\n");
		sb.append("   `~\\/").append("\n");
		sb.append("      |").append("\n");
		sb.append("      |").append("\n");
		System.out.println(sb);
	}
}