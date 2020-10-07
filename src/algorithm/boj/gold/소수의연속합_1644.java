package algorithm.boj.gold;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 소수의연속합_1644 {
	public static void main(String[] args) {
		// 1. 부분합을 이용해서 구하기
		// 2. 파라매트릭 서치로도 가능함
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		List<Integer> primes = getPrimes(n);
		
		int[] sum = new int[primes.size() + 1];
		for (int i = 1; i < sum.length; i++) {
			sum[i] = sum[i - 1] + primes.get(i - 1);
		}
		
		int res = 0;
		for (int l = 0; l < sum.length; l++) {
			for (int r = l + 1; r < sum.length; r++) {
				int tmp = sum[r] - sum[l];
				
				if (tmp == n) res++;
				else if (tmp > n) break;
			}
		}
		
		System.out.println(res);
	}
	
	static List<Integer> getPrimes(int n) {
		List<Integer> res = new ArrayList<>();
		
		boolean[] primes = new boolean[n + 1];
		
		primes[0] = primes[1] = true;
		for (int i = 2; i * i <= n; i++) {
			if (primes[i]) continue;
			for (int j = i * 2; j <= n; j += i)
				primes[j] = true;
		}
		
		for (int i = 2; i < primes.length; i++)
			if (!primes[i]) res.add(i);
		
		return res;
	}
}
