package string;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class string_05 {
	static int[] arr;
	static String[] dnaArr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		//DNA 입력받기
		dnaArr = new String[n]; 
		for(int i = 0 ; i < n ; i++) {
			dnaArr[i] = br.readLine();
		}
		int Distance = 0;
		StringBuilder sb = new StringBuilder();
		//한자리씩 빈도 수 알아보기 - A,C,T,G 순서대로 이루어짐
		for(int i = 0 ; i < m ; i++) {
			arr = new int[4];
			//모든 DNA 단어 방문
			for(int j = 0 ; j < n ; j++) {
				countDNA(i,j);
			}
			//최대값 저장함
			int maxCnt = 0;
			int select = 0;
			for( int z = 0 ; z < 4 ; z++ ) {
				if( maxCnt < arr[z] ) {
					maxCnt = arr[z];
					select = z;
				}
			}
			Distance += (n - maxCnt);
			appendChar(select,sb);
			
		}
		sb.append("\n").append(Distance);
		System.out.println(sb);
		
	}
	private static void appendChar(int select, StringBuilder sb) {
		//문자 추가하기
		if( select == 0 ) {
			sb.append('A');
		}else if ( select == 1 ) {
			sb.append('C');
		}else if ( select == 2 ) {
			sb.append('G');
		}else {
			sb.append('T');
		}
	}
	// DNA 계산하기
	private static void countDNA(int index, int row) {
		char dna = dnaArr[row].charAt(index);
		if( dna == 'A' ) {
			arr[0]++;
		}else if(dna == 'C' ) {
			arr[1]++;
		}else if(dna == 'G' ) {
			arr[2]++;
		}else {
			arr[3]++;
		}
	}
}