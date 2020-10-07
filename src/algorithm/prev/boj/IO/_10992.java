package algorithm.prev.boj.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10992 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		for(int i = 1 ; i <= a - 1 ; i++) {
			for (int j = 0; j < a - i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < i * 2 - 1; j++) {
				if(j == 0 || j == i * 2 - 2)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		
		for (int i = 0; i < a * 2 - 1; i++) {
			System.out.print("*");
		}
	}

}
