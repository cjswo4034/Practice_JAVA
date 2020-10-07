package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1373 {
	static String trans(String num) {
		return String.valueOf(Integer.parseInt(num, 2));
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		
		String str = br.readLine();
		if(str.length() % 3 != 0) {
			for(int i = 0 ; i < str.length() % 3 ; i++) {
				str = "0" + str;
			}
		}
		
		for(int i = 0 ; i < str.length() ; i += 3) {
			ans.append(trans(str.substring(i, i + 3)));
		}
		
		System.out.println(ans);
	}
}
