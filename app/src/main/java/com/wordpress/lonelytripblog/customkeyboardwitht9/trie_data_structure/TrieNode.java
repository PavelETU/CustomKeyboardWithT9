package com.wordpress.lonelytripblog.customkeyboardwitht9.trie_data_structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Node implementation for trie data structure.
 * The key point is to keep set with numbers of elements that match current leaf.
 */

public class TrieNode {
    Map<Character, TrieNode> child = new HashMap<>();
    Set<Integer> correspondingNumbersInList = new HashSet<>();

    public TrieNode() {}

//    public TrieNode(char c) {
//        this.c = c;
//    }
}
