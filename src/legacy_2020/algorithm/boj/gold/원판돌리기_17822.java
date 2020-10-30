package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 원판돌리기_17822 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int[][] arr = initArr(br, n, m);
        
        for (int i = 0; i < t; i++) {
        	st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());	// 배수가 num인 원판을 dir방향(0:시계, 1:반시계)으로 cnt번 회전시켜라.
			boolean isClockwise = Integer.parseInt(st.nextToken()) == 0;
			int cnt = Integer.parseInt(st.nextToken());
			System.out.println(i);
			display(arr);
			
			for (int j = num - 1; j < n; j += num) {
				rotate(arr[j], isClockwise, cnt);
			}

			display(arr);
			
			if (!subtract(arr)) {
				display(arr);
				setting(arr);
			}
			
			display(arr);
		}
        System.out.println(getSum(arr));
    }
	
	static void display(int[][] list) {
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[0].length; j++) {
				System.out.print(list[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("--------------------");
	}
	
	static void rotate(int[] list, boolean isClockwise, int cnt) {
		int[] rotatedList = new int[list.length];
		if (isClockwise) {
			for (int i = cnt; i < rotatedList.length; i++)
				rotatedList[i] = list[i - cnt];
			for (int i = 0; i < cnt; i++)
				rotatedList[i] = list[list.length - cnt + i];
		} else {
			for (int i = cnt; i < rotatedList.length; i++)
				rotatedList[i - cnt] = list[i];
			for (int i = 0; i < cnt; i++)
				rotatedList[list.length - cnt + i] = list[i];
		}
		System.arraycopy(rotatedList, 0, list, 0, list.length);
	}
	
	static boolean subtract(int[][] arr) {
		boolean res = false;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] != -1 && dfs(arr, i, j, arr[i][j])) {
					res = true;
				}
			}
		}
		return res;
	}
	
	static boolean dfs(int[][] arr, int row, int col, int value) {
		boolean res = false;
		int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		for (int i = 0; i < direction.length; i++) {
			int nr = row + direction[i][0];
			int nc = col + direction[i][1];
			
			if (nr < 0 || nr >= arr.length) continue;
			if (nc < 0) nc = arr[0].length - 1;
			if (nc >= arr[0].length) nc = 0;
			if (arr[nr][nc] == value) {
				res = true;
				arr[nr][nc] = -1;
				dfs(arr, nr, nc, value);
			}
		}
		
		return res;
	}
	
	static void setting(int[][] arr) {
		double avg = getAvg(arr);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == -1) continue;
				if (arr[i][j] < avg) arr[i][j]++;
				else if (arr[i][j] != avg) arr[i][j]--;
			}
		}
	}
	
	static int getSum(int[][] arr) {
		int sum = 0;
		for (int[] li: arr) {
			for (int el: li) {
				if (el == -1) continue;
				sum += el;
			}
		}
		return sum;
	}
	
	static double getAvg(int[][] arr) {
		double cnt = 0, sum = 0;
		for (int[] li: arr) {
			for (int el: li) {
				if (el == -1) continue;
				cnt++;
				sum += el;
			}
		}
		if (cnt == 0) return 0;
		return sum / cnt;
	}
	
	static int[][] initArr(BufferedReader br, int r, int c) throws IOException{
		int[][] res = new int[r][c];
		StringTokenizer st;
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++)
				res[i][j] = Integer.parseInt(st.nextToken());
		}
		return res;
	}
}
