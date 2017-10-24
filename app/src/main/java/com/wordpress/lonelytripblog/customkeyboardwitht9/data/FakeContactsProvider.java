package com.wordpress.lonelytripblog.customkeyboardwitht9.data;

import com.wordpress.lonelytripblog.customkeyboardwitht9.trie_data_structure.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fake contacts provider. Used in mock build variant.
 */

public class FakeContactsProvider implements ContactsProviderContract {
    private List<Contact> contacts;

    public FakeContactsProvider() {
        contacts = new ArrayList<>(Arrays.asList(
                new Contact("Dummy", "+792325"),
                new Contact("Old", "93845"),
                new Contact("New", "9857**23"),
                new Contact("Dusty", "983"),
                new Contact("Without", null),
                new Contact(null, null),
                new Contact(null, "****"),

                new Contact("Wd", "984597438")));
    }

    @Override
    public void provideContacts(ProvideContactsCallback callback) {
        callback.onSuccess(contacts);
    }

    @Override
    public void provideTrie(ProvideTrieCallback callback) {
        Trie trie = new Trie();
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.getName() != null) {
                trie.insertWord(contact.getName(), i);
            }
            if (contact.getNumber() != null) {
                trie.insertNumber(contact.getNumber(), i);
            }
        }
        callback.onSuccess(trie);
    }
}
