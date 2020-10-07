package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// Gold 3
public class 알수없는문장_1099 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		int[] dp = new int[input.length + 1];
		
		int n = Integer.parseInt(br.readLine());
		
		char[][] strs = new char[n][];
		for (int i = 0; i < n; i++) {
			strs[i] = br.readLine().toCharArray();
		}
		
		Arrays.fill(dp, -1);
		dp[0] = 0;
		
		for (int i = 1; i <= input.length; i++) {
			for (char[] str : strs) {
				// 1. 순회중인 문자의 길이가 짧은지 확인
				if (str.length > i) continue;
				
				// 2. 순회중인 문자의 길이 이전의 원소가 -1이 아닌지 확인
				// 3-1. -1이면 pass
				if (dp[i - str.length] == -1) continue;
				
				// 3-2. -1이 아니라면 현재 위치(i) ~ 현재 위치 - 순회중인 문자의 길이 의 문자들이 순회중인 문자들로 이루어졌는지 확인
				if (equalTo(input, i - str.length, i, str)) {
					// 4. 1) 구성 문자가 같다면 최소 비용을 확인하여 저장	
					if (dp[i] == -1) 
						dp[i] = dp[i - str.length] + calculateCost(input, i - str.length, str);
					else 
						dp[i] = Math.min(dp[i], dp[i - str.length] + calculateCost(input, i - str.length, str));
				}
			}
		}
		
		System.out.println(dp[input.length]);
	}
	
	private static boolean equalTo(char[] src, int strFrom, int strTo, char[] dest) {
		int[] srcCnt = new int[26];
		int[] destCnt = new int[26];
		
		for (int i = strFrom; i < strTo; i++)
			srcCnt[src[i] - 'a']++;
		
		for (char ch: dest)
			destCnt[ch - 'a']++;
		
		for (int i = 0; i < 26; i++)
			if (srcCnt[i] != destCnt[i]) return false;
		
		return true;
	}
	
	
	private static int calculateCost(char[] src, int strFrom, char[] dest) {
		int res = 0;
		for (int i = 0; i < dest.length; i++)
			if (src[strFrom + i] != dest[i]) res++;
		
		return res;
	}
}
