package algorithm.boj.gold;

import java.util.Scanner;

public class X합_1081 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int l = sc.nextInt();
		int u = sc.nextInt();

		System.out.println(function(u));
		System.out.println(function(l - 1));
		System.out.println(function(u) - function(l - 1));
	}
	
	// 왜 틀린지 모르겠음. 책페이지 통과한거 보면 로직은 맞는데 어느 부분에서 틀린건지...
	// l과 u를 int -> long으로 바꿨을 때도 틀림
	private static long function(int num) {
		int len = String.valueOf(num).length();
		
		long[] count = new long[10];
		for (int i = 0, size = (int)Math.pow(10, len - 1); i < len; i++, size /= 10) {
			int l = num / (size * 10);
			int m = num / size % 10;
			int r = num % size;
			
			for (int j = 0; j < 10; j++) {
				count[j] += l * size;
				if (j < m) count[j] += size;
			}
			
			count[m] += r + 1;
			count[0] -= size;
		}
		
		long res = 0;
		for (int i = 0; i < count.length; i++) {
			res += i * count[i];
		}
		
		return res;
	}
	
	private static long solution(int num) {
		int len = String.valueOf(num).length();
		
		long[] count = new long[10];
		for (int i = 0, size = (int)Math.pow(10, len - 1); i < len; i++, size /= 10) {
			int l = num / (size * 10);
			int m = num / size % 10;
			int r = num % size;
			
			for (int j = 0; j < 10; j++) {
				count[j] += l * size;
				if (j < m) count[j] += size;
			}
			
			count[m] += r + 1;
			count[0] -= size;
		}

		long res = 0;
		for (int i = 0; i < count.length; i++) {
			res += i * count[i];
		}
		
		return res;
	}
}
