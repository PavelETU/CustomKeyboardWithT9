package com.wordpress.lonelytripblog.customkeyboardwitht9;

import com.wordpress.lonelytripblog.customkeyboardwitht9.data.Contact;

import java.util.List;

/**
 * Interfaces for View and Presenter in MVP pattern
 */

public class Contract {
    interface View {
        void displayRequestedContacts(List<Contact> data);
        void deleteLastCharacter();
        void deleteAllCharacters();
        void displayEmptyView();
    }
    interface Presenter {
        void requestContacts(String input);
    }
}
