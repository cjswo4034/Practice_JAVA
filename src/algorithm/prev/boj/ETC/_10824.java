package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10824 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] st = br.readLine().split(" ");
		
		long a = Long.parseLong(st[0] + st[1]);
		long b = Long.parseLong(st[2] + st[3]);
		
		System.out.println(a + b);
	}
}