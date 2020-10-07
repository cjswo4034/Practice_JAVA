package algorithm.prev.programmers.Level3;

import java.util.Arrays;

// 1. 2^n - 1 크기를 갖는 배열을 초기화
// 2. arr[0] = 0;
// 3. arr[n] = arr[n] + 0 + arr[n] ^ 1
public class 종이접기 {
	public static void main(String[] args) {
		종이접기 sol = new 종이접기();
		System.out.println(Arrays.toString(sol.solution(3)));
	}

	public int[] solution(int n) {
		int[] answer = new int[(1 << n) - 1];

		for (int i = 2, currLen; i <= n; i++) {
			currLen = (1 << i) - 1;
			decalcomania(answer, currLen / 2);
		}

		return answer;
	}

	private void decalcomania(int[] arr, int mid) {
		for (int i = 1; i <= mid; i++) {
			arr[i + mid] = arr[mid - i] ^ 1;
		}
	}
}