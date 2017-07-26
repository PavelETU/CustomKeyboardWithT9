package com.wordpress.lonelytripblog.customkeyboardwitht9.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fake contacts provider. Used in mock build variant.
 */

public class ContactsProvider implements ContactsProviderContract {
    @Override
    public List<Contact> provideContacts() {
        List<Contact> result = new ArrayList<>(Arrays.asList(
                new Contact("Dummy", "Name", "+792325"),
                new Contact("Old", "Friend", "93845"),
                new Contact("New", "Friend", "9857**23"),
                new Contact("Dusty", "Mr", "983"),
                new Contact("Without", "number", null),
                new Contact(null, null, null),
                new Contact(null, "Ms", "****"),
                new Contact("Wd", null, "984597438")));
        return result;
    }
}
