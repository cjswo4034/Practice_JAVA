package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1978 {
	static boolean check[] = new boolean[1001];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine()), max = 0, cnt = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i++) {
			int num = Integer.parseInt(st.nextToken());
			check[num] = true;
			max = Math.max(max, num);
		}
		for(int i = 2; i <= max ; i++) {
			if(check[i]) {
				for(int j = i * 2 ; j <= max ; j += i) {
					check[j] = false;
				}
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
