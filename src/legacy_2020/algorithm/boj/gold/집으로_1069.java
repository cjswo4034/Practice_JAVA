package algorithm.boj.gold;

import java.util.Scanner;

//Gold 2
public class 집으로_1069 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int d = sc.nextInt();
		int t = sc.nextInt();
		
		double a = Math.sqrt(x * x + y * y);	// 집까지 가는 길
		double answer = a;
		int n = (int)(a / d);
		
		// 1) 걸어간다. (d <= t 일 때)
		// 2) n번 점프 후 남은 거리를 걸어간다.

		// 3) 집이 아닌 방향으로 점프 후, 집으로 점프했을 때 집이다. (n == 0 일 때)
		// 4) 1번 점프 후 되돌아간다. (n == 0 일 때)
		
		// 5) n + 1번 점프 했을 때 집이다. (n > 0 일 때)
		
		answer = a;	 									// 1)
		answer = Math.min(answer, a - n * d + n * t); 	// 2)
		
		if (n == 0) {
			answer = Math.min(answer, t * 2.0);			// 3)
			answer = Math.min(answer, d - a + t);		// 4)
		} else answer = Math.min(answer, n * t + t);	// 5)
		
		System.out.println(answer);
	}
}
