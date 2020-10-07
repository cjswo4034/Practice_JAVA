package algorithm.prev.programmers.Level3;

import java.util.stream.IntStream;

public class _43163 {
    public static void main(String[] args) {
        _43163 prob = new _43163();
        System.out.println(prob.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(prob.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"}));
    }

    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        int answer = dfs(begin, target, words, visited, 0);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private int dfs(String current, String target, String[] words, boolean[] visited, int depth){
        if (current.equals(target)) return depth;
        else {
            int answer = Integer.MAX_VALUE;
            for (int i = 0; i < words.length; i++) {
                if (visited[i]) continue;
                if (checkTest(current, words[i])) {
                    visited[i] = true;
                    answer = Math.min(answer, dfs(words[i], target, words, visited, depth + 1));
                    visited[i] = false;
                }
            }
            return answer;
        }
    }

    private boolean check(String begin, String word){
        for (int i = 0, cnt = 0; i < begin.length(); i++) {
            if (begin.charAt(i) != word.charAt(i)) cnt++;
            if (cnt > 1) return false;
        }
        return true;
    }

    public boolean checkTest(String begin, String word){
        return IntStream.range(0, begin.length())
                .filter(value -> (begin.charAt(value) != word.charAt(value)))
                .count() == 1;
    }
}
