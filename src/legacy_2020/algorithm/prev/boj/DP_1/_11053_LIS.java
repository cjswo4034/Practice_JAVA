package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _11053_LIS {
	static int A[] = new int[1001];
	static int DP[] = new int[1001];
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n ; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int size = 1;
		DP[1] = A[1];
		
		for(int i = 2; i <= n ; i++) {
			if(DP[size] < A[i]) {
				size++;
				DP[size] = A[i];
				continue;
			}
			
			lowerBound(n, A[i]);
		}
		
		for (int i = 1; i <= size; i++) {
			System.out.println(DP[i]);
		}
		System.out.println(size);
	}
	
	public static void lowerBound(int idx, int value) {
		for(int i = 1 ; i <= idx ; i++) {
			if(DP[i] >= value) {
				DP[i] = value;
				break;
			}
		}
	}

}
