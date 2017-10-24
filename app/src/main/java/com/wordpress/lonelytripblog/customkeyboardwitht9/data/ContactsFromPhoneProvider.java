package com.wordpress.lonelytripblog.customkeyboardwitht9.data;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import com.wordpress.lonelytripblog.customkeyboardwitht9.trie_data_structure.Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Павел on 23.10.2017.
 */

public class ContactsFromPhoneProvider implements ContactsProviderContract {

    private List<Contact> contacts;
    private static ContactsFromPhoneProvider INSTANCE;
    private Trie mTrie;
    private Context mContext;
    private Uri uri = ContactsContract.Contacts.CONTENT_URI;

    private ContactsFromPhoneProvider(Context context) {
        mContext = context;
    }

    public static ContactsFromPhoneProvider getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ContactsFromPhoneProvider(context);
        }
        return INSTANCE;
    }

    // Lazy initialization of contacts in provideContacts
    @Override
    public void provideContacts(ProvideContactsCallback callback) {
        if (contacts == null) {
            contacts = new ArrayList<>();
            ContentResolver mContentResolver = mContext.getContentResolver();
            Cursor cursor = mContentResolver.query(uri, null, null, null, null);
            int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            int displayNameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            int hasPhoneIndex = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
            while (cursor.moveToNext()) {
                String contactId = cursor.getString(idIndex);
                String name = cursor.getString(displayNameIndex);
                if (cursor.getInt(hasPhoneIndex) != 0) {
                    Cursor phones = mContentResolver
                            .query(Phone.CONTENT_URI,
                                    null, Phone.CONTACT_ID + " = " + contactId, null, null);
                    int phoneIndex = phones.getColumnIndex(Phone.NUMBER);
                    while (phones.moveToNext()) {
                        String number = phones.getString(phoneIndex);
                        if (number == null) continue;
                        contacts.add(new Contact(name, number));
                    }
                    phones.close();
                } else {
                    if (name == null) {
                        continue;
                    }
                    contacts.add(new Contact(name, null));
                }
            }
            cursor.close();
        }
        callback.onSuccess(contacts);
    }

    // Lazy initialization in provideTrie for mTrie
    @Override
    public void provideTrie(ProvideTrieCallback callback) {
        if (mTrie == null) {
            mTrie = new Trie();
            for (int i = 0; i < contacts.size(); i++) {
                Contact contact = contacts.get(i);
                if (contact.getName() != null) {
                    mTrie.insertWord(contact.getName(), i);
                }
                if (contact.getNumber() != null) {
                    mTrie.insertNumber(contact.getNumber(), i);
                }
            }
        }
        callback.onSuccess(mTrie);
    }
}
