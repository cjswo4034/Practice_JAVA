package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1212 {
	static String _2to8(int num, int con) {
		StringBuilder result = new StringBuilder();
		int n = num;
		while (n > 0) {
			result.append(n % 2);
			n /= 2;
		}
		
		if(result.length() < 3 && con != 1) {
			for (int i = 0, length = 3 - result.length() % 3; i < length; i++) {
				result.append("0");
			}
		}

		return result.reverse().toString();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();

		String str = br.readLine();
		ans.append(_2to8(str.charAt(0) - '0', 1));
		for (int i = 1; i < str.length(); i++) {
			ans.append(_2to8(str.charAt(i) - '0', 0));
		}
		
		if(ans.length() == 0) {
			System.out.println(0);
		} else
			System.out.println(ans);
	}
}
