package algorithm.programmers.kakao;

import java.util.Stack;

public class 크레인_인형뽑기 {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> s = new Stack<>();
        
        for (int move : moves) {
			for (int [] b : board) {
				int target = b[move - 1];
				if (target == 0) continue;
				b[move - 1] = 0;

				if (!s.isEmpty() && s.peek() == target) {
					s.pop();
					answer += 2;
				} else s.add(target);
				
				break;
			}
		}
        return answer;
    }
}
