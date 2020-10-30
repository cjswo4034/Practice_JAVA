package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열2_12015 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int[] lb = new int[n + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
        
        int size = 1;
        lb[1] = arr[1];
        
        for (int i = 2; i <= n; i++) {
			if (lb[size] < arr[i]) {
				lb[++size] = arr[i];
				continue;
			}
			
			int pos = lowerBound(lb, size, arr[i]);
			lb[pos] = arr[i];
		}
        
        System.out.println(size);
	}
	
	// value 보다 큰 값 중 가장 작은 값
	static int lowerBound(int[] arr, int to, int value) {
		int l = 1, r = to, mid = (l + r) / 2;
		while (l < r) {
			mid = (l + r) >> 1;
			if (arr[mid] > value) r = mid;
			else if (arr[mid] < value) l = mid + 1;
			else return mid;
		}
		return r;
	}
}
