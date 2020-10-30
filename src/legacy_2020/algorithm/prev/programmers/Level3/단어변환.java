package algorithm.prev.programmers.Level3;

public class 단어변환 {
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"}));
		System.out.println(solution.solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log"}));
	}

}

class Solution {
	// 그래프로 접근
	// 다음 노드를 검사할 때 길이가 같고 한글자 차이가 있는지 확인
	// 바꿀 수 없으면 987654321 반환, 있으면 가장 짧은 길이 반환
	
	private static final int MAX = 987654321;
	private int result;
	public int solution(String begin, String target, String[] words) {
		result = MAX;
		for (int i = 0; i < words.length; i++) {
			dfs(0, new boolean[words.length], begin, target, words);
		}
		return result == MAX ? 0 : result;
	}
	
	private void dfs(int depth, boolean[] visited, String curr, String target, String[] words) {
		if (curr.equals(target)) {
			result = Math.min(result, depth);
			return;
		}
		
		if (result <= depth) return;

		for (int i = 0; i < words.length; i++) {
			if (!visited[i] && enableChange(curr, words[i])) {
				visited[i] = true;
				dfs(depth + 1, visited, words[i], target, words);
				visited[i] = false;
			}
		}
	}
	
	private boolean enableChange(String from, String to) {
		if (from.length() != to.length()) return false;
		
		boolean flag = false;
		for (int i = 0; i < from.length(); i++) {
			if (from.charAt(i) != to.charAt(i) && flag) return false;
			else if (from.charAt(i) != to.charAt(i)) flag = true;
		}
		
		return flag;
	}
}