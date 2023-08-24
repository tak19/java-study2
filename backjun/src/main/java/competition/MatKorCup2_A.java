package competition;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MatKorCup2_A {
	static int N,M,K;
	static long[] tree;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s = br.readLine();
		switch (s) {
		case "M":
			s = "MatKor";
			break;
		case "W":
			s = "WiCys";
			break;
		case "C":
			s = "CyKor";
			break;
		case "A":
			s = "AlKor";
			break;
		case "$":
			s = "$clear";
			break;
		}
		System.out.println(s);
	}
}