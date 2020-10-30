package algorithm.prev.programmers.kakaoRecruit_2019;

import java.util.Arrays;
import java.util.HashMap;

// fail (효율성 3개 틀림)
public class _4 {
    public static void main(String[] args) {
        _4 prob = new _4();
        System.out.println(Arrays.toString(prob.solution(
                new String[]{"frodo", "front", "frost", "frozen", "frame", "kakao"},
                new String[]{"fro??", "????o", "fr???", "fro???", "pro?"}
        )));
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        HashMap<String, Integer> map = new HashMap<>();
        initMap(map, words);

        int idx = 0;
        for (String query : queries)
            answer[idx++] = map.getOrDefault(query, 0);

        return answer;
    }

    private void initMap(HashMap<String, Integer> map, String[] words){
        String pre, post;
        for (String word : words){
            for (int i = 1; i < word.length(); i++) {
                pre = transToQuestion(word.substring(0, i), word.length() - i, true);
                post = transToQuestion(word.substring(i), i, false);

                if (map.containsKey(pre)) map.put(pre, map.get(pre) + 1);
                else map.put(pre, 1);

                if (map.containsKey(post)) map.put(post, map.get(post) + 1);
                else map.put(post, 1);
            }
        }
    }

    private String transToQuestion(String str, int cnt, boolean prefix){
        StringBuilder sb = new StringBuilder();
        if (prefix) sb.append(str);
        while (cnt-- > 0) sb.append("?");
        if (!prefix) sb.append(str);
        return sb.toString();
    }

    /*public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        ArrayList<String>[] lists = new ArrayList[10001];
        for (int i = 0; i < lists.length; i++) lists[i] = new ArrayList<>();
        for (String word : words) lists[word.length()].add(word);

        int idx = 0;
        String tmp;
        for (String query : queries){
            int flag = preOrPost(query), cnt = 0; // 0 : ?로 시작, 1 : ?가 뒤에있음
            tmp = query.replaceAll("[?]+", "");
            for (String word : lists[query.length()]){
                if (flag == 0 && word.endsWith(tmp)) cnt++;
                else if (flag == 1 && word.startsWith(tmp)) cnt++;
            }
            answer[idx++] = cnt;
        }

        return answer;
    }
     */
}
