package etc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class etc_25 {
	static int N,D,K,C;
	static int[] arr,window;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 접시 수, 초밥 가지수, 연속 접시 수, 쿠폰 번호
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		window = new int[D+1]; //초밥 가지수 만큼의 윈도우
		//초밥 입력 받았음
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int select = 0;
		//초기 슬라이딩 윈도우만큼 스시를 추가함
		for(int i = 0 ; i < K ; i++) {
			if( window[arr[i]] == 0 ) {
				select++; //초밥 종류 증가 시킴
			}
			window[arr[i]]++;
		}
		//초기 시작점의 초밥 개수 세팅
		int max = select;
		
		
		//나머지 경우를 탐색해야하는경우 - 슬라이딩 윈도우 사용
		for(int i = 1 ; i < N ; i++) {
			if( max <= select ) {
				//쿠폰 안 초밥 안 먹었다면 추가
				if( window[C] == 0 ) {
					max = select + 1;
				}else {
					max = select;
				}
				
			}
			//끝 인덱스 원소 더하고 앞쪽 인덱스 원소 빼줌
			int addIndex = (i + K-1) % N;
			if( window[arr[addIndex]] == 0 ) {
				select++;
			}
			window[arr[addIndex]]++;
			
			window[arr[i-1]]--;
			if(window[arr[i-1]]==0) {
				select--;
			}
				
			
		}
		
		System.out.println(max);
	}


}
