package algorithm.prev.programmers.kakaoRecruit_2017;

import java.util.Arrays;

public class 자동완성 {
    public static void main(String[] args) {
        자동완성 prob = new 자동완성();
        System.out.println(prob.solution(new String[]{"go", "gone", "guild"}));
        System.out.println(prob.solution(new String[]{"abc","def","ghi","jklm"}));
        System.out.println(prob.solution(new String[]{"word","war","warrior","world"}));
    }

    public int solution(String[] words) {
        Arrays.sort(words);
        int answer = Math.min(getEqualCount(words[0], words[1]) + 1, words[0].length());
        String prev, post;
        for (int i = 1; i < words.length - 1; i++) {
            prev = words[i - 1];
            post = words[i + 1];
            answer += Math.min(Math.max(getEqualCount(words[i], prev), getEqualCount(words[i], post)) + 1, words[i].length());
        }
        answer += Math.min(getEqualCount(words[words.length - 2], words[words.length - 1]) + 1, words[words.length - 1].length());
        return answer;
    }

    private int getEqualCount(String curr, String target){
        int idx = 0;
        while (curr.charAt(idx) == target.charAt(idx)){
            idx++;
            if (idx >= curr.length() || idx >= target.length()) break;
        }
        return idx;
    }
}