package com.wordpress.lonelytripblog.customkeyboardwitht9;

import com.wordpress.lonelytripblog.customkeyboardwitht9.data.Contact;
import com.wordpress.lonelytripblog.customkeyboardwitht9.data.ContactsProvider;
import com.wordpress.lonelytripblog.customkeyboardwitht9.data.ContactsProviderContract;

import java.util.List;

/**
 * Business logic goes here.
 * Presenter in MVP pattern.
 */

public class InputHandler implements Contract.Presenter {

    private Contract.View view;

    private List<Contact> allContacts;

    public InputHandler(Contract.View view) {
        this.view = view;
        allContacts = new ContactsProvider().provideContacts();
    }



    /**
     * Key function in the whole program.
     * It will implement T9 algorithm and sent all appropriate contacts to the view.
     * @param input - string that contains user input
     *              valid characters - '1','2','3','4','5','6','7','8','9','0','*' and '#'
     */
    @Override
    public void requestContacts(String input) {

        view.displayRequestedContacts(allContacts);

    }
}
