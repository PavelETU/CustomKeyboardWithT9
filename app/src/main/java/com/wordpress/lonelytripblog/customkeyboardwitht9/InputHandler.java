package com.wordpress.lonelytripblog.customkeyboardwitht9;

import com.wordpress.lonelytripblog.customkeyboardwitht9.data.Contact;
import com.wordpress.lonelytripblog.customkeyboardwitht9.data.ContactsProvider;
import com.wordpress.lonelytripblog.customkeyboardwitht9.data.ContactsProviderContract;
import com.wordpress.lonelytripblog.customkeyboardwitht9.trie_data_structure.Trie;
import com.wordpress.lonelytripblog.customkeyboardwitht9.trie_data_structure.TrieNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Business logic goes here.
 * Presenter in MVP pattern.
 */

public class InputHandler implements Contract.Presenter {

    private Contract.View view;

    private List<Contact> allContacts;
    private Trie mTrie;

    public InputHandler(Contract.View view) {
        this.view = view;
        ContactsProviderContract provider = new ContactsProvider();
        allContacts = provider.provideContacts();
        mTrie = provider.provideTrie();
    }



    /**
     * Key function in the whole program.
     * It will implement T9 algorithm and sent all appropriate contacts to the view.
     * @param input - string that contains user input
     *              valid characters - '1','2','3','4','5','6','7','8','9','0','*' and '#'
     */
    @Override
    public void requestContacts(String input) {
        if (input.equals("")) {
            view.displayRequestedContacts(allContacts);
            return;
        }
        Set<Integer> setWithNumbers = mTrie.search(input);
        if (setWithNumbers == null) {
            view.displayEmptyView();
            return;
        }
        List<Contact> result = new ArrayList<>();
        for (Integer index : setWithNumbers) {
            result.add(allContacts.get(index));
        }
        view.displayRequestedContacts(result);

    }
}
