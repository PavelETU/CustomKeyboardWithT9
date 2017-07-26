package com.wordpress.lonelytripblog.customkeyboardwitht9.data;

import java.util.List;

/**
 * Interface that should be implemented by ContactsProviders (mocking and real contacts).
 */

public interface ContactsProviderContract {
    List<Contact> provideContacts();
}
