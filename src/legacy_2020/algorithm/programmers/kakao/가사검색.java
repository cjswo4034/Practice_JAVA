package algorithm.programmers.kakao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 가사검색 {
	public static void main(String[] args) {
		가사검색 prob = new 가사검색();
		int[] res = prob.solution(new String[] {"frodo", "front", "frost", "frozen", "frame", "kakao"}, new String[] {"fro??", "????o", "fr???", "fro???", "pro?"});
		System.out.println(Arrays.toString(res));
		res = prob.solution(new String[] {"a", "b"}, new String[] {"?"});
		System.out.println(Arrays.toString(res));
	}
	
	public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        Trie trie = new Trie();
        
        for (String word: words) trie.add(word, false);
        for (int i = 0; i < queries.length; i++) {
			if (queries[i].startsWith("?") && queries[i].endsWith("?")) 
				answer[i] = trie.root.count.getOrDefault(queries[i].length(), 0);
			else answer[i] = trie.find(queries[i], queries[i].startsWith("?"));
		}
        
        return answer;
    }
	
	class Trie {
		TrieNode root, reversedRoot;
		
		public Trie() {
			this.root = new TrieNode();
			this.reversedRoot = new TrieNode();
		}

		public void add(String word, boolean reversal) {
			int len = word.length();
			TrieNode runner;
			
			if (reversal)  runner = this.reversedRoot;
			else {
				add(reverseStr(word), true);
				runner = this.root;
			}

			char ch;
			for (int i = 0; i < len; i++) {
				ch = word.charAt(i);
				if (runner.count.get(len - i) == null) runner.count.put(len - i, 0);
				runner.count.computeIfPresent(len - i, (key, value) -> ++value);
				
				if (runner.child.get(ch) == null) runner.child.put(ch, new TrieNode());
				runner = runner.child.get(ch);
			}
		}

		public int find(String query, boolean reversal) {
			int len = query.length();
			TrieNode runner = this.root;
			
			if (reversal) {
				runner = this.reversedRoot;
				query = reverseStr(query);
			}
			
			char ch;
			for (int i = 0; i < len; i++) {
				ch = query.charAt(i);
				if (ch == '?') return runner.count.getOrDefault(len - i, 0);
				if (runner.child.get(ch) == null) return 0;
				runner = runner.child.get(ch);
			}
			
			return 0;
		}

		class TrieNode {
			Map<Character, TrieNode> child = new HashMap<>();
			Map<Integer, Integer> count = new HashMap<>();
		}
	}
	
	static String reverseStr(String str) {
		StringBuilder sb = new StringBuilder(str);
		return sb.reverse().toString();
	}
}
