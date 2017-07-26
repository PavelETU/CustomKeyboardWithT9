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
    @Override
    public List<Contact> provideContacts() {
        List<Contact> result = new ArrayList<>(Arrays.asList(
                new Contact("Real", "Name", "+792325"),
                new Contact("Real", "Friend", "93845")));
        return result;
    }
}
