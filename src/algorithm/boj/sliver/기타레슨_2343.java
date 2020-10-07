package algorithm.boj.sliver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Silver 1
public class 기타레슨_2343 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		long l = 1, r = 0;
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			r += arr[i];
			// 하나의 트랙을 두개의 CD에 분할할 수 없기 때문에 정답은 전체 트랙중 가장 긴 것보다 같거나 크다.
			l = Math.max(arr[i], l);
		}
		
		long ans = 0, m, cnt, sum;
		while (l <= r) {
			m = (l + r) >> 1;
			cnt = 1;
			sum = 0;
			for (int el: arr) {
				if (el + sum <= m) sum += el;
				else {
					sum = el;
					cnt += 1;
				}
			}
			
			System.out.printf("%d %d %d, cnt: %d\n", l, m, r, cnt);
			if (cnt <= k) {
				r = m - 1;
				ans = m;
			} else {
				l = m + 1;
			}
		}
		
		System.out.println(ans);
	}
}
