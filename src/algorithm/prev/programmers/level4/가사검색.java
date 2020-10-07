package algorithm.prev.programmers.level4;

import java.util.*;
import java.util.stream.Collectors;

public class 가사검색 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(
                new String[]{"frodo", "front", "frost", "frozen", "frame", "kakao"},
                new String[]{"frozen", "fro?", "fro??", "????o", "fr???", "fro???", "pro?"}
        )));
    }

    static class Solution {
        public int[] solution(String[] words, String[] queries) {
            int[] answer = new int[queries.length];
            Trie root = new Trie();
            for (String word : words) insert(root, word);
            for (int i = 0; i < queries.length; i++) {
                answer[i] = search(root, queries[i]);
            }
            return answer;
        }

        private int search(Trie root, String query){
            Queue<Trie> q = new LinkedList<>();
            Trie node = root;
            q.add(node);

            int len;
            char ch;
            Trie next = null;
            for (int i = 0; i < query.length() - 1; i++) {
                ch = query.charAt(i);
                len = q.size();
                while (len-- > 0) {
                    next = q.poll();
                    if (ch == '?')
                        q.addAll(next.getTries());
                    else if (next.map.get(ch) != null){
                        q.add(next.map.get(ch));
                    }
                }
            }

            // treminate인 것만
            int count = 0;
            char lastChar = query.charAt(query.length() - 1);
            if (lastChar == '?') {
                // q.poll 의 map 에서 terminate인 것
                List<Trie> list;
                while (!q.isEmpty()){
                    list = new ArrayList<>(q.poll().map.values());
                    for (Trie trie : list){
                        if (trie.terminate) count++;
                    }
                }
            }
            else {
                while (!q.isEmpty()){
                    next = q.poll().map.get(lastChar);
                    if (next != null && next.terminate)
                        count++;
                }
            }
            return count;
        }

        private void insert(Trie root, String word){
            Trie node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                Trie next = node.map.get(ch);
                if (next == null){
                    next = new Trie();
                    node.map.put(ch, next);
                }
                node = next;
            }
            node.terminate = true;
        }
    }

    static class Trie {
        private Map<Character, Trie> map = new HashMap<>();
        private boolean terminate;

        public List<Trie> getTries(){
            return new ArrayList<>(map.values());
        }
    }
}
