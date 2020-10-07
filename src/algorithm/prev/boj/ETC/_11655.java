package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11655 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char [] st = br.readLine().toCharArray();
		
		for(int i = 0 ; i < st.length ; i++) {
			int tmp = st[i];
			if(Character.isUpperCase(st[i])) {
				tmp += 13;
				if(tmp > 'Z') {
					tmp = tmp - 'Z' + 'A' - 1;
				}
			} else if(Character.isLowerCase(st[i])) {
				tmp += 13;
				if(tmp > 'z') {
					tmp = tmp - 'z' + 'a' - 1;
				}
			}
			System.out.print((char)tmp);
		}
	}
}