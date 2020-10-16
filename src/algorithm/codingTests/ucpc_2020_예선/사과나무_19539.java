package algorithm.codingTests.ucpc_2020_예선;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 각각의 사과나무에 할당되는 2의 개수가 전체 사과의 수 / 3 보다 커야된다.
 * */
public class 사과나무_19539 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), sum = 0, two = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0, tmp; i < n; i++) {
            tmp = Integer.parseInt(st.nextToken());
            two += tmp / 2;
            sum += tmp;
        }

        if (sum % 3 != 0 || sum / 3 > two) {
            System.out.println("NO");
            return;
        }

        System.out.println("YES");
    }
}
