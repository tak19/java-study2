package etc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
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
		//초밥 종류를 입력 받았음 - 인덱스는 순서
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int select = 0;
		int max = 0;
        HashSet<Integer> sushiSet = new HashSet<>();
        // 초기 슬라이딩 윈도우 설정 - 종류 별로 카운팅 증가 -> 중복은 증가 안함
        for (int i = 0; i < K; i++) {
            window[arr[i]]++;
            if (window[arr[i]] == 1) {
                select++;
            }
            sushiSet.add(arr[i]);
        }
        //최대값을 현재 종류와 쿠폰을 확인한 종류로 갱신
        max = select + (sushiSet.contains(C) ? 0 : 1);

        // 슬라이딩 윈도우 이동하며 최대 초밥 종류 개수 갱신
        for (int i = K; i < N + K; i++) {
            int removeIdx = (i - K) % N; // 제거 초밥 인덱스
            window[arr[removeIdx]]--;
            if (window[arr[removeIdx]] == 0) {
                select--;
                sushiSet.remove(arr[removeIdx]);
            }
            //추가 초밥 인덱스
            int addIdx = i % N;
            window[arr[addIdx]]++;
            if (window[arr[addIdx]] == 1) {
                select++;
                sushiSet.add(arr[addIdx]);
            }

            max = Math.max(max, select + (sushiSet.contains(C) ? 0 : 1));
        }

        System.out.println(max);
    }
}