package com.wordpress.lonelytripblog.customkeyboardwitht9.data;

import com.wordpress.lonelytripblog.customkeyboardwitht9.trie_data_structure.Trie;

import java.util.List;

/**
 * Interface that should be implemented by ContactsProviders (mocking and real contacts).
 */

public interface ContactsProviderContract {
    List<Contact> provideContacts();
    Trie provideTrie();
}
