package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// gold 5
public class 단어수학_1339 {
    private static int wordCount, result;
    private static char[] chars;
    private static String[] words;
    private static boolean[] visited;
    private static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        String word;
        int[] arr = new int[26];
        for (int i = 0; i < t; i++) {
            word = br.readLine();
            for (int j = 0, len = word.length(); j < word.length(); j++)
                arr[word.charAt(len - j - 1) - 'A'] += Math.pow(10, j);
        }

        Arrays.sort(arr);
        int res = 0;
        for (int i = 9, idx = 25; i >= 0; i--) {
            res += arr[idx--] * i;
        }
        System.out.println(res);
    }

    private static void solution1() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()), i;
        words = new String[t];
        visited = new boolean[10];
        for (i = 0; i < t; i++) {
            words[i] = br.readLine();
            for (int j = 0; j < words[i].length(); j++)
                map.putIfAbsent(words[i].charAt(j), -1);
        }
        i = 0;
        wordCount = map.size();
        chars = new char[wordCount];
        for (Character ch: map.keySet()) chars[i++] = ch;
        backtracking(0);
        System.out.println(result);
    }

    // 문자의 수
    private static void backtracking(int depth) {
        if (depth == wordCount) {
            result = Math.max(result, sumWords());
            return;
        }

        char ch = chars[depth];
        for (int i = 0; i < 10; i++) {
            if (!visited[i]) {
                visited[i] = true;
                map.put(ch, i);
                backtracking(depth + 1);
                map.put(ch, -1);
                visited[i] = false;
            }
        }
    }

    // 문자 -> 숫자
    private static int convertToInt(String word) {
        int res = 0;
        for (int i = 0; i < word.length(); i++) {
            res *= 10;
            res += map.get(word.charAt(i));
        }
       return res;
    }

    // 문자들 더하기
    private static int sumWords() {
        int res = 0;
        for (String word: words) res += convertToInt(word);
        return res;
    }
}
