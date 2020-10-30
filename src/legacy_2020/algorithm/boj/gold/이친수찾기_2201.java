package algorithm.boj.gold;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 이친수찾기_2201 {
	private static long MAX_K = 1_000_000_000_000_000_000L;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		List<Long> list = initList(MAX_K);
		System.out.println(solution(n, list));
	}
	
	private static String solution(long num, List<Long> list) {
		// 1) 큰 값 중 가장 작은 list 원소
		int lowerBound = lowerBound(num, list);
		// 2) num - 1)
		long diff = num - list.get(lowerBound);
		// 3) 2) == 0 이면 변환 후 리턴
		if (diff == 0) return convertToStr(lowerBound);
		// 4) 2) != 0 이면 concat(convert(2)), solution(2), list));
		return or(convertToStr(lowerBound), solution(diff, list));
	}
	
	// 1) 큰 값 중 가장 작은 list 원소
	private static int lowerBound(long num, List<Long> list) {
		int i = 0;
		for (; i < list.size(); i++) {
			if (num <= list.get(i)) return num == list.get(i) ? i : i - 1;
		}
		return i - 1;
	}
	
	// 2) 입력받은 cnt 갯수만큼 0을 덧붙혀서 리턴
	private static String convertToStr(int cnt) {
		StringBuilder sb = new StringBuilder("1");
		for (int i = 0; i < cnt; i++) {
			sb.append('0');
		}
		return sb.toString();
	}
	
	// 3) 두 개의 String을 입력받은 후 or 연산
	private static String or(String max, String min) {
		if (max.length() < min.length()) return or(min, max);
		StringBuilder sb = new StringBuilder();
		int maxLen = max.length();
		int minLen = min.length();
		for (int i = 0, len = maxLen - minLen; i < len; i++) {
			sb.append(max.charAt(i));
		}
		for (int i = maxLen - minLen, j = 0; i < maxLen; i++, j++) {
			if (max.charAt(i) != min.charAt(j) || max.charAt(i) == '1') sb.append('1');
			else sb.append('0');
		}
		return sb.toString();
	}
	
	private static List<Long> initList(long num) {
		List<Long> list = new ArrayList<>();
		list.add(1l);
		list.add(2l);
		long a = 1, b = 2, sum = a + b;
		for (; sum <= num;) {
			list.add(sum);
			a = b;		
			b = sum;
			sum = a + b;
		}
		return list;
	}
}
