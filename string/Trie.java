package ds_algo_study.string;

import java.util.HashMap;
import java.util.Map;

class Trie {
    Trie root;
    String prefix;
    Map<Character, Trie> children;
    boolean isWord;
    
    /** Initialize your data structure here. */
    public Trie() {
        children = new HashMap<>();
        root = new Trie("");
    }
    public Trie(String s) {
        this.prefix = s;
        children = new HashMap<>();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new Trie(word.substring(0, i + 1)));
            }
            curr = curr.children.get(c);
            if (i == word.length() - 1) {
                curr.isWord = true;
            }
        }
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children.containsKey(c)) {
                curr = curr.children.get(c);
            } else {
                return false;
            }
        }
        return curr.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie curr = root;
        for (char c : prefix.toCharArray()) {
            if (curr.children.containsKey(c)) {
                curr = curr.children.get(c);
            } else {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */