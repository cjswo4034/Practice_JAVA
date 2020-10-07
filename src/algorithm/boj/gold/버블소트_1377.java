package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 버블소트_1377 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Pair[] arr = new Pair[n];
        
        for (int i = 0; i < n; i++) 
        	arr[i] = new Pair(i, Integer.parseInt(br.readLine()));
        
        Arrays.sort(arr, (p1, p2) -> p1.value == p2.value ? Integer.compare(p1.idx, p2.idx) : Integer.compare(p1.value, p2.value));
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
			if (answer < arr[i].idx - i) answer = arr[i].idx - i;
		}
        System.out.println(answer + 1);
        
        // 1. arr -> Input arr
        // 2. sorted -> sort(arr)
        // 3. mark -> boolean[n];
        // 일 때, 움직일 필요가 없는 원소들의 개수를 구한다.
        
        // answer = 0;
        // for(i ~ n)
        //   if (sorted[answer] == arr[i])
        //     answer++
        //     while (mark[answer]) answer++;
        //   else mark[index[arr[i]]] = true;
	}
	
	static class Pair {
		int idx, value;
		public Pair(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}
	}
}
