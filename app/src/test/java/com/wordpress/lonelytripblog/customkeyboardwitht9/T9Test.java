package com.wordpress.lonelytripblog.customkeyboardwitht9;

import com.wordpress.lonelytripblog.customkeyboardwitht9.data.Contact;
import com.wordpress.lonelytripblog.customkeyboardwitht9.data.ContactsProvider;
import com.wordpress.lonelytripblog.customkeyboardwitht9.data.ContactsProviderContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * Test for major function in InputHandler.
 *
 *
 *
 * Verifying our algorithm against fake contacts provider (in .../src/mock directory).
 * Contacts declared in provider
 * (name, surname, number):
 * "Dummy", "Name", "+792325"
 * "Old", "Friend", "93845"
 * "New", "Friend", "9857**23"
 * "Dusty", "Mr", "983"
 * "Without", "number", null
 * null, null, null
 * null, "Ms", "****"
 * "Wd", null, "984597438"
 */


@RunWith(Parameterized.class)
public class T9Test {

    public T9Test(String userInput, List<Contact> returnedContacts) {
        this.userInput = userInput;
        this.returnedContacts = returnedContacts;
    }

    private ContactsProviderContract mContactsProvider;

    @Mock
    private Contract.View mView;


    private Contract.Presenter mPresenter;

    @Captor
    private ArgumentCaptor<List<Contact>> captor;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new InputHandler(mView);
        mContactsProvider = new ContactsProvider();
    }

    public String userInput;
    public List<Contact> returnedContacts;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
            {"Dummy", new ArrayList<>(Arrays.asList(new Contact("Dummy", "Name", "+792325")))},
            {"Friend", new ArrayList<>(Arrays.asList(new Contact("Old", "Friend", "93845")))},
            {"9857**23", new ArrayList<>(Arrays.asList(new Contact("New", "Friend", "9857**23")))}
            };
        return Arrays.asList(data);
    }

    @Test
    public void verifyAlgorithm() throws Exception {
        mPresenter.requestContacts(userInput);
        verify(mView).displayRequestedContacts(captor.capture());
        assertEquals("Result", returnedContacts, captor.getValue());
    }

}