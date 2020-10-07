package algorithm.prev.programmers.Level3;

public class N으로표현 {
	public static void main(String[] args) {
		System.out.println(solution(5, 12));
		System.out.println(solution(2, 11));
	}
	
	public static int solution(int N, int number){
        int ans = dfs(N, number, 0, 0);
        return ans <= 8 ? ans : -1;
    }
	
	private static int dfs(int N, int number, int curr, int depth) {
		if (depth > 8 || (curr == 0 && depth > 0)) return Integer.MAX_VALUE;
		if (curr == number) return depth;
		
		int result = Integer.MAX_VALUE;
		
		int nextN = 0, nextDepth = depth;
		for (int i = 0, leng = 8 - depth; i < leng; i++) {
			nextN = nextN * 10 + N;
			nextDepth += 1;
			result = Math.min(dfs(N, number, curr + nextN, nextDepth), result);
			result = Math.min(dfs(N, number, curr - nextN, nextDepth), result);
			result = Math.min(dfs(N, number, curr * nextN, nextDepth), result);
			result = Math.min(dfs(N, number, curr / nextN, nextDepth), result);
		}
		
		return result;
	}
}
