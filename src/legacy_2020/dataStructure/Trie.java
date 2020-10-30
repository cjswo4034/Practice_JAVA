package dataStructure;

public class Trie {
    private TrieNode root;

    public Trie(String[] words){
        this.root = new TrieNode();
        for (String word : words){
            this.root.insert(word);
        }
    }

    public int findWord(String word){
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            node = node.getChildren(word.charAt(i));
            if (node == null){
                return -1;
            }
        }
        return node.count;
    }

    // qm만 insert 할 수 있는 노드 만들기
    class TrieNode {
        private TrieNode[] childrens;
        private boolean terminate;
        private int count;

        public TrieNode(){
            this.childrens = new TrieNode[26];
            this.terminate = false;
            this.count = 0;
        }

        public void insert(String str){
            if (str == null || str.length() == 0) return;

            int firstChar = str.charAt(0) - 'a';

            TrieNode child = childrens[firstChar];
            if (child == null){
                child = new TrieNode();
                childrens[firstChar] = child;
            }

            if (str.length() > 1) {
                child.insert(str.substring(1));
                child.setTerminate(false);
            } else
                child.setTerminate(true);

            child.count++;
        }

        public TrieNode getChildren(char ch) {
            return childrens[ch - 'a'];
        }

        public void setTerminate(boolean terminate) {
            this.terminate = terminate;
        }

        public boolean isTerminate() {
            return terminate;
        }
    }
}
