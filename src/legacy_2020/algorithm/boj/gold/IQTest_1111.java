package algorithm.boj.gold;

import java.util.Scanner;

public class IQTest_1111 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
		
		if (n == 1) {
			System.out.println('A');
		} else if (n == 2) {
			// arr[0] == arr[1] ? arr[0] : 'A'
			if (arr[0] == arr[1])
				System.out.println(arr[0]);
			else System.out.println('A');
		} else {
			int x = arr[1] - arr[0];
			int y = arr[2] - arr[1];
			int d = x == 0 ? 0 : y / x;
			int a = arr[1] - arr[0] * d;
			
			boolean flag = true;
			for (int i = 0; i < n - 1; i++) {
				if ((arr[i] * d + a) != arr[i + 1]) {
					flag = false;
					break;
				}
			}
			if (flag) System.out.println(arr[n - 1] * d + a);
			else System.out.println('B');
		}
	}
}
