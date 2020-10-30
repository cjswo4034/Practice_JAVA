package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2048_12100 {
	static int n, ans;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        
        for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        dfs(arr, 0);
        System.out.println(ans);
	}
	
	static void dfs(int[][] arr, int depth) {
		if (depth == 5) {
			for (int[] r : arr) for (int el: r) ans = Math.max(ans, el);
			return;
		}
		int[][] copyArr = new int[n][n];
		
		copyArr(arr, copyArr);
		mergeLeft(copyArr);
		dfs(copyArr, depth + 1);
		
		copyArr(arr, copyArr);
		mergeRight(copyArr);
		dfs(copyArr, depth + 1);

		copyArr(arr, copyArr);
		mergeUp(copyArr);
		dfs(copyArr, depth + 1);

		copyArr(arr, copyArr);
		mergeDown(copyArr);
		dfs(copyArr, depth + 1);
	}
	
	static void copyArr(int[][] src, int[][] dest) {
		for (int i = 0; i < n; i++)
			System.arraycopy(src[i], 0, dest[i], 0, n);
	}

	static void mergeLeft(int[][] arr) {
		for (int i = 0; i < n; i++) {
			for (int j = 0, k; j < n - 1; j++) {
				if (arr[i][j] > 0) {
					k = j + 1;
					while (k < n && arr[i][k] == 0) k += 1;
					if (k < n && arr[i][j] == arr[i][k]) {
						arr[i][j] *= 2;
						arr[i][k] = 0;
						j = k;
					}
				}
			}
			
			for (int j = 0, k; j < n - 1; j++) {
				if (arr[i][j] == 0) {
					k = j + 1;
					while (k < n && arr[i][k] == 0) k += 1;
					if (k < n && arr[i][k] != 0) {
						arr[i][j] = arr[i][k];
						arr[i][k] = 0;
					}
				}
			}
		}
	}
	
	static void mergeRight(int[][] arr) {
		for (int i = 0; i < n; i++) {
			for (int j = n - 1, k; j > 0; j--) {
				if (arr[i][j] > 0) {
					k = j - 1;
					while (k >= 0 && arr[i][k] == 0) k -= 1;
					if (k >= 0 && arr[i][j] == arr[i][k]) {
						arr[i][j] *= 2;
						arr[i][k] = 0;
						j = k;
					}
				}
			}
			
			for (int j = n - 1, k; j > 0; j--) {
				if (arr[i][j] == 0) {
					k = j - 1;
					while (k >= 0 && arr[i][k] == 0) k -= 1;
					if (k >= 0 && arr[i][k] != 0) {
						arr[i][j] = arr[i][k];
						arr[i][k] = 0;
						j = k;
					}
				}
			}
		}
	}
	
	static void mergeUp(int[][] arr) {
		for (int i = 0; i < n; i++) {
			for (int j = 0, k; j < n - 1; j++) {
				if (arr[j][i] > 0) {
					k = j + 1;
					while (k < n && arr[k][i] == 0) k += 1;
					if (k < n && arr[j][i] == arr[k][i]) {
						arr[j][i] *= 2;
						arr[k][i] = 0;
						j = k;
					}
				}
			}
			
			for (int j = 0, k; j < n - 1; j++) {
				if (arr[j][i] == 0) {
					k = j + 1;
					while (k < n && arr[k][i] == 0) k += 1;
					if (k < n && arr[k][i] != 0) {
						arr[j][i] = arr[k][i];
						arr[k][i] = 0;
					}
				}
			}
		}
	}
	
	static void mergeDown(int[][] arr) {
		for (int i = 0; i < n; i++) {
			for (int j = n - 1, k; j > 0; j--) {
				if (arr[j][i] > 0) {
					k = j - 1;
					while (k >= 0 && arr[k][i] == 0) k -= 1;
					if (k >= 0 && arr[j][i] == arr[k][i]) {
						arr[j][i] *= 2;
						arr[k][i] = 0;
						j = k;
					}
				}
			}
			
			for (int j = n - 1, k; j > 0; j--) {
				if (arr[j][i] == 0) {
					k = j - 1;
					while (k >= 0 && arr[k][i] == 0) k -= 1;
					if (k >= 0 && arr[k][i] != 0) {
						arr[j][i] = arr[k][i];
						arr[k][i] = 0;
					}
				}
			}
		}
	}
}
