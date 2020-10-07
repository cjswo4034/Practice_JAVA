package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _4522 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String str = br.readLine();
			sb.append("#" + t);
			boolean check = func(str);
			
			if(!check) {
				sb.append(" Not exist\n");
			} else {
				sb.append(" Exist\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static boolean func(String str) {
		String left, right;
		if(str.length() % 2 == 0) {
			left = str.substring(0, str.length() / 2);
			right = reverse(str.substring(str.length() / 2, str.length()));
			
			for (int i = 0, leng = left.length(); i < leng; i++) {
				if(left.charAt(i) != right.charAt(i)) {
					if(left.charAt(i) == '?' || right.charAt(i) == '?')
						continue;
					else
						return false;
				}
			}
			
			return true;
		} else {
			left = str.substring(0, str.length() / 2);
			right = reverse(str.substring((str.length() / 2) + 1, str.length()));
			
			for (int i = 0, leng = left.length(); i < leng; i++) {
				if(left.charAt(i) != right.charAt(i)) {
					if(left.charAt(i) == '?' || right.charAt(i) == '?')
						continue;
					else
						return false;
				}
			}
			
			return true;
		}
	}
	
	static String reverse(String str) {
		return new StringBuilder(str).reverse().toString();
	}

}
