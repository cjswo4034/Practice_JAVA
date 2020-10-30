package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10808 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char [] st = br.readLine().toCharArray();
		int [] a = new int[26];
		for(int i = 0 ; i < st.length ; i++) {
			a[st[i] - 'a']++;
		}
		
		for(int i = 0 ; i < 26 ; i++) {
			System.out.print(a[i] + " ");
		}
	}

}
