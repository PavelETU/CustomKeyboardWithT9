package com.wordpress.lonelytripblog.customkeyboardwitht9;

import android.content.Context;
import android.support.annotation.NonNull;

import com.wordpress.lonelytripblog.customkeyboardwitht9.data.ContactsFromPhoneProvider;
import com.wordpress.lonelytripblog.customkeyboardwitht9.data.ContactsProviderContract;

/**
 * Created by Павел on 23.10.2017.
 */

public class Injection {
    public static ContactsProviderContract provideContacts(@NonNull Context context) {
        return ContactsFromPhoneProvider.getInstance(context);
    }
}
