package algorithm.prev.programmers.level4;

import java.util.Arrays;
import java.util.Scanner;

public class _4단고음 {
	public static void main(String[] args) {
		System.out.println(863 + " : " + solution(863));
//		for (int i = 1; i <= 1000; i++) {
//			System.out.println(i + " : " + solution(i));
//		}
	}
	
	static int solution(int n) {
		int mul = 1, answer = 0;
		long min, max;
		while (true) {
			min = (long)Math.pow(3, mul) + mul * 2;
			max = getMax(mul * 2);
			if (n < min) break;
			if (min <= n && n <= max) {
				answer = backTracking(1, 0, mul, mul * 2, n);
				break;
			}
			mul++;
		}
		return answer;
	}
	
    static int dfs(int n, int cnt) {
        if(n < 3 || n < Math.pow(3, cnt / 2)) return 0;
        if(n == 3) {
            if (cnt == 2) return 1;
        } else if(n > 3) {
            if (n % 3 == 0 && cnt >= 2) {
                dfs(n / 3, cnt - 2);
            }
            return dfs(n - 1, cnt + 1);
        }
        return 0;
    }
    
	static int backTracking(int value, int pos, int mul, int plus, int n) {
        if (n < value) return 0;
		if (mul < 0 || plus < mul * 2) return 0;
		if (mul == 0 && plus == 0) {
			return value == n ? 1 : 0;
		}
		int res = 0;
		res += backTracking(value * 3, pos + 1, mul - 1, plus, n);
		res += backTracking(value + 1, pos + 1, mul, plus - 1, n);
		return res;
	}
	
	static long getMax(int n) {
		long res = 1;
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) res *= 3;
			else res += 2;
		}
		return res;
	}
	
	static long calculate(boolean[] musicScore) {
		long res = 1;
		for (boolean score: musicScore) {
			if (score) res *= 3;
			else res += 1;
		}
		return res;
	}
	
	static void display(boolean[] musicScore) {
		for (boolean score: musicScore) {
			if (score) System.out.print('3');
			else System.out.print('1');
		}
		System.out.println();
	}
}