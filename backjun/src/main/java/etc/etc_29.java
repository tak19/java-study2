package etc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class etc_29 {
	static int N,H;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        // 길이와 높이를 입력받음
        N = Integer.parseInt(inputs[0]);
        H = Integer.parseInt(inputs[1]);

        int[] crush = new int[H + 2]; // 배열 크기를 H+2로 설정

        // 석순과 종유석이 번갈아 등장함
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(br.readLine());

            if (i % 2 == 1) {
                // 홀수 번째는 석순이므로 바닥부터 부수는 위치를 업데이트 - n까지 벽 생성 위해
                crush[1]++;
                crush[n + 1]--;
            } else {
                // 짝수 번째는 종유석이므로 천장부터 부수는 위치를 업데이트
                crush[H - n + 1]++;
                crush[H + 1]--;
            }
        }

        int min = N; // 초기 최소값은 전체 벽의 개수로 설정
        int minCnt = 0;

        // 부숴진 벽의 개수 누적하여 최소값과 최소값의 개수 계산
        for (int i = 1; i <= H; i++) {
            crush[i] += crush[i - 1];
            if (crush[i] < min) {
                min = crush[i];
                minCnt = 1;
            } else if (crush[i] == min) {
                minCnt++;
            }
        }

        System.out.println(min + " " + minCnt);
    }
}