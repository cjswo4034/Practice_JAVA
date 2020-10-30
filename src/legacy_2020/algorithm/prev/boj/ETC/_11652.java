package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class _11652 {
	static BigInteger a[];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		a = new BigInteger[n];
		for(int i = 0 ; i < n ; i++) {
			a[i] = new BigInteger(br.readLine());
		}
		
		Arrays.sort(a);
		
		BigInteger num_A = a[0];
		BigInteger num_B = a[0];
		
		int cnt_A = 0;
		int cnt_B = 0;
		
		for(int i = 0 ; i < n ; i++) {
			if(!num_B.equals(a[i])) {
				if(cnt_B > cnt_A) {
					cnt_A = cnt_B;
					num_A = num_B;
				} 
				cnt_B = 1;
				num_B = a[i];
			} else {
				cnt_B++;
			}
		}
		
		if(cnt_B > cnt_A) {
			cnt_A = cnt_B;
			num_A = num_B;
		}
		
		System.out.println(num_A);

	}
}
