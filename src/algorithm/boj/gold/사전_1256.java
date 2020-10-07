package algorithm.boj.gold;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class 사전_1256 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int z = sc.nextInt();
		BigInteger k = BigInteger.valueOf(sc.nextInt());
		BigInteger[][] dict = new BigInteger[a + 1][z + 1];
		String answer;
		
		// 굳이 BIgInteger로 하지 않아도 10억이 넘으면 10억 1로 초기화하여 해결가능하다.
		Arrays.fill(dict[0], BigInteger.valueOf(1));
		for (int i = 1; i <= a; i++) {
			dict[i][0] = BigInteger.valueOf(1);
			for (int j = 1; j <= z; j++)
				dict[i][j] = dict[i - 1][j].add(dict[i][j - 1]);
		}
	
		if (dict[a][z].compareTo(k) == -1 || k.compareTo(BigInteger.valueOf(1000000000)) == 1) answer = "-1";
		else answer = function(dict, a, z, k);
		
		System.out.println(answer);
	}
	
	static String function(BigInteger[][] arr, int a, int z, BigInteger k) {
		if (a == 0) return strMul('z', z);
		if (z == 0) return strMul('a', a);
		
		StringBuilder res = new StringBuilder();
		BigInteger criterion = arr[a - 1][z];
		
		if (criterion.compareTo(k) == -1) res.append('z').append(function(arr, a, z - 1, k.subtract(criterion)));
		else res.append('a').append(function(arr, a - 1, z, k));
		return res.toString();
	}
	
	static String strMul(char ch, int count) {
		StringBuilder sb = new StringBuilder();
		while (count-- > 0) sb.append(ch);
		return sb.toString();
	}
}
