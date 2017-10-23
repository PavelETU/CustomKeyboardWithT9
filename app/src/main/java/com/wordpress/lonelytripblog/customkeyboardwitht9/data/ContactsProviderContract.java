package com.wordpress.lonelytripblog.customkeyboardwitht9.data;

import com.wordpress.lonelytripblog.customkeyboardwitht9.trie_data_structure.Trie;

import java.util.List;

/**
 * Interface that should be implemented by ContactsProviders (mocking and real contacts).
 */

public interface ContactsProviderContract {
    interface ProvideContactsCallback {
        void onSuccess(List<Contact> result);
        void onFailed(String message);
    }
    interface ProvideTrieCallback {
        void onSuccess(Trie result);
        void onFailed(String message);
    }
    void provideContacts(ProvideContactsCallback callback);
    void provideTrie(ProvideTrieCallback callback);
}
