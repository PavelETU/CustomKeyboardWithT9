package com.wordpress.lonelytripblog.customkeyboardwitht9.data;

import com.wordpress.lonelytripblog.customkeyboardwitht9.data.Contact;
import com.wordpress.lonelytripblog.customkeyboardwitht9.data.ContactsProviderContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contacts provider. Should be production ready
 */

public class ContactsProvider implements ContactsProviderContract {
    private List<Contact> contacts;

    public ContactsProvider() {
        contacts = new ArrayList<>(Arrays.asList(
                new Contact("Dummy", "Name", "+792325"),
                new Contact("Old", "Friend", "93845"),
                new Contact("New", "Friend", "9857**23"),
                new Contact("Dusty", "Mr", "983"),
                new Contact("Without", "number", null),
                new Contact(null, null, null),
                new Contact(null, "Ms", "****"),
                new Contact("Wd", null, "984597438")));
    }

    @Override
    public List<Contact> provideContacts() {
        return contacts;
    }

    @Override
    public Trie provideTrie() {
        Trie trie = new Trie();
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.getName() != null) {
                trie.insertWord(contact.getName(), i);
            }
            if (contact.getSurName() != null) {
                trie.insertWord(contact.getSurName(), i);
            }
            if (contact.getNumber() != null) {
                trie.insertNumber(contact.getNumber(), i);
            }
        }
        return trie;
    }
}
