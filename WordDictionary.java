import java.util.Arrays;

public class WordDictionary {

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (current.branches[ch - 'a'] == null) {
                current.branches[ch - 'a'] = new TrieNode();
            }
            current = current.branches[ch - 'a'];
        }
        current.isWord = true;
    }

    public boolean search(String word) {
        return search(word.toCharArray(), root);
    }

    public boolean search(char[] word, TrieNode node) {

        TrieNode current = node;
        for (int i = 0; i < word.length; i++) {
            char ch = word[i];
            if (ch == '.') {
                return searchByReplacingDotsWithLetters(word, i, current);
            }
            if (current.branches[ch - 'a'] == null) {
                return false;
            }
            current = current.branches[ch - 'a'];
        }
        return current.isWord;
    }

    public boolean searchByReplacingDotsWithLetters(char[] word, int index, TrieNode current) {
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (current.branches[ch - 'a'] != null) {
                word[index] = ch;
                if (search(Arrays.copyOfRange(word, index, word.length), current)) {
                    return true;
                }
            }
        }
        return false;
    }
}

class TrieNode {

    TrieNode[] branches;
    final int CHARS_IN_ALPHABET = 26;
    boolean isWord;

    public TrieNode() {
        branches = new TrieNode[CHARS_IN_ALPHABET];
    }
}
