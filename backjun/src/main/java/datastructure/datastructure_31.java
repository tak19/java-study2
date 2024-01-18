package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

// L * TC = TC수가 왜 안주워짐...?
public class datastructure_31 {
	static StringBuilder result = new StringBuilder();
	static int n;
	static String s = "";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// TC수
		n = Integer.parseInt(br.readLine());

		List<Character> list = null;
		ListIterator<Character> listIt = null;

		StringBuilder sb = null;
		// TC진행
		for (int test = 0; test < n; test++) {
			list = new LinkedList<Character>();
			listIt = list.listIterator();
			s = br.readLine();

			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '<') {
					if(listIt.hasPrevious()) {
						listIt.previous();
					}
				} else if (s.charAt(i) == '>') {
					if(listIt.hasNext()) {
						listIt.next();
					}
				} else if (s.charAt(i) == '-') {
					if(listIt.hasPrevious()) {
						listIt.previous();
						listIt.remove();
					}
				} else {
					listIt.add(s.charAt(i));
				}
			}
			for(char c : list) {
				result.append(c);
			}
			result.append("\n");
		}
		System.out.println(result);

	}

}