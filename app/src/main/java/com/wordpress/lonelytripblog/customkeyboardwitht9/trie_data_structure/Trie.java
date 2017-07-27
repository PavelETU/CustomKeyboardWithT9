package com.wordpress.lonelytripblog.customkeyboardwitht9.trie_data_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Trie data structure.
 */

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public Set<Integer> search(String target) {
        Map<Character, TrieNode> child = root.child;
        for (int i = 0; i < target.length(); i++) {
            char currentChar = target.charAt(i);
            // If there are no corresponding contacts - return null
            if (child.get(currentChar) == null) return null;
            // Last character - return set, otherwise - move down the trie
            if ( i == (target.length() - 1) ) {
                return child.get(currentChar).correspondingNumbersInList;
            } else {
                child = child.get(currentChar).child;
            }
        }
        return null;
    }

    public void insertWord(String word, int correspondingNumberInList) {
        word = word.toLowerCase();
        Map<Character, TrieNode> child = root.child;
        for (int i = 0; i < word.length(); i++) {
            Character c = transformToNumber(word.charAt(i));
            // If current character not valid - skip it
            if (c == null) {
                continue;
            }
            // If this key doesn't exist - create node.
            if (!child.containsKey(c)) {
                child.put(c, new TrieNode());
            }
            // Update set and move down the trie.
            child.get(c).correspondingNumbersInList.add(correspondingNumberInList);
            child = child.get(c).child;

        }
    }

    public void insertNumber(String number, int correspondingNumberInList) {
        number = number.toLowerCase();
        number = trimNumber(number);
        List<Map<Character, TrieNode>> children = new ArrayList<>();
        for (int i = 0; i < number.length(); i++) {
            children.add(root.child);
            Character c = number.charAt(i);
            for (int j = 0; j <= i; j++) {
                Map<Character, TrieNode> child = children.get(j);
                // If this key doesn't exist - create node.
                if (!child.containsKey(c)) {
                    child.put(c, new TrieNode());
                }
                // Update set and move down the trie.
                child.get(c).correspondingNumbersInList.add(correspondingNumberInList);
                children.set(j, child.get(c).child);
            }
        }
    }

    // Trim number in case it contains not valid symbols or '+'
    // Valid characters = {'1', '2', '3','4','5','6','7','8',
    //              '9','0','*','+','#'}
    private String trimNumber(String number) {
        StringBuilder stringBuilder = new StringBuilder();
        String validCharacters = "1234567890*#";
        for (int i = 0; i < number.length(); i ++) {
            Character c = number.charAt(i);
            if (validCharacters.contains(c.toString())) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    // Valid characters = {'1', '2', '3','4','5','6','7','8',
    //              '9','0','a','b','c','d','e','f','g','h','i','j','k','l',
    //              'm','n','o','p','q','r','s','t','u','v','w','x','y','z','*','+','#'}
    private Character transformToNumber(char c) {
        switch (c) {
            case 'a':
            case 'b':
            case 'c':
                return '2';
            case 'd':
            case 'e':
            case 'f':
                return '3';
            case 'g':
            case 'h':
            case 'i':
                return '4';
            case 'j':
            case 'k':
            case 'l':
                return '5';
            case 'm':
            case 'n':
            case 'o':
                return '6';
            case 'p':
            case 'q':
            case 'r':
            case 's':
                return '7';
            case 't':
            case 'u':
            case 'v':
                return '8';
            case 'w':
            case 'x':
            case 'y':
            case 'z':
                return '9';
            case '+':
                return '0';
            case '*':
            case '#':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return c;
            default:
                return null;
        }
    }


}
