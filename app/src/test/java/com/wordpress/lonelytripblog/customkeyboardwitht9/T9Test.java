package com.wordpress.lonelytripblog.customkeyboardwitht9;

import com.wordpress.lonelytripblog.customkeyboardwitht9.data.Contact;
import com.wordpress.lonelytripblog.customkeyboardwitht9.data.FakeContactsProvider;
import com.wordpress.lonelytripblog.customkeyboardwitht9.data.ContactsProviderContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
 * (name, number):
 * "Dummy", "+792325"
 * "Old", "93845"
 * "New", "9857**23"
 * "Dusty", "983"
 * "Without", null
 * null, null
 * null, "****"
 * "Wd", "984597438"
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
        mPresenter = new InputHandler(new FakeContactsProvider(), mView);
        mContactsProvider = new FakeContactsProvider();
    }

    public String userInput;
    public List<Contact> returnedContacts;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {  // "Dummy" = "38669", "Friend" = "374363"
            {"38669", new ArrayList<>(Arrays.asList(new Contact("Dummy", "+792325")))},
            {"3", new ArrayList<>(Arrays.asList(
                    new Contact("Dummy", "+792325"),
                    new Contact("Old", "93845"),
                    new Contact("New", "9857**23"),
                    new Contact("Dusty", "983"),
                    new Contact("Wd", "984597438")))},
            {"74363", new ArrayList<>()}, // ummy
            {"74", new ArrayList<>(Arrays.asList(new Contact("Wd", "984597438")))},
            {"792325", new ArrayList<>(Arrays.asList(new Contact("Dummy", "+792325")))},
            {"2985798372598732948723", new ArrayList<>()},
            {"9857**23", new ArrayList<>(Arrays.asList(new Contact("New", "9857**23")))}
            };
        return Arrays.asList(data);
    }

    @Test
    public void verifyAlgorithm() throws Exception {
        mPresenter.requestContacts(userInput);
        if (userInput.equals("2985798372598732948723") || userInput.equals("74363")) {
            verify(mView).displayEmptyView();
        } else {
            verify(mView).displayRequestedContacts(captor.capture());
            //assertEquals("Result", returnedContacts, captor.getValue());
            assertTrue(captor.getValue().get(0).getName(), returnedContacts.containsAll(captor.getValue()) &&
                    returnedContacts.size() == captor.getValue().size());
        }
    }


}
