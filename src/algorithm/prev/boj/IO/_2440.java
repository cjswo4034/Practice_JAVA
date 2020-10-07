package algorithm.prev.boj.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2440 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < a ; i++) {
			for (int j = 0; j < a - i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
