package algorithm.boj.platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/* [Platinum 3] Trie
 *  - 다음과 같은 경우에 횟수를 증가한다.
 *    1. 현재 누를 수 있는 자판이 2개 이상인 경우.
 *    2. 현재 누른 자판으로 끝나는 단어가 있는 경우.
 * */
public class 휴대폰자판_5670 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        Trie trie;

        int n;
        double ans;
        char[][] queries;
        while ((line = br.readLine()) != null) {
            n = Integer.parseInt(line);
            trie = new Trie();
            queries = new char[n][];
            for (int i = 0; i < n; i++) {
                queries[i] = br.readLine().toCharArray();
                trie.insert(queries[i]);
            }

            ans = Stream.of(queries)
                    .mapToInt(trie::query)
                    .sum();

            System.out.printf("%.2f\n", ans / n);
        }
    }

    static class Trie {
        static TrieNode root;

        Trie() { root = new TrieNode(); }

        void insert(char[] word) {
            TrieNode runner = root;
            for (char ch : word) {
                if (runner.getTrieNode(ch) == null) {
                    runner.setTrieNode(ch);
                    runner.child++;
                }
                runner = runner.getTrieNode(ch);
            }
            runner.isTerminal = true;
        }

        int query(char[] word) {
            int res = 0;
            TrieNode runner = root;
            for (char ch: word) {
                runner = runner.getTrieNode(ch);
                if (runner.child > 1 || runner.isTerminal) res++;
            }
            return res;
        }

        static class TrieNode {
            TrieNode[] tries = new TrieNode[26];
            boolean isTerminal;
            int child = 0;

            TrieNode getTrieNode(char ch) {
                return tries[ch - 'a'];
            }

            void setTrieNode(char ch) {
                tries[ch - 'a'] = new TrieNode();
            }
        }
    }
}