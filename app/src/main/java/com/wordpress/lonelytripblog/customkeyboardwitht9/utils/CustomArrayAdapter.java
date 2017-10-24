package com.wordpress.lonelytripblog.customkeyboardwitht9.utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wordpress.lonelytripblog.customkeyboardwitht9.R;
import com.wordpress.lonelytripblog.customkeyboardwitht9.data.Contact;

import java.util.List;

/**
 * Custom ArrayAdapter (for displaying in ListView).
 */

public class CustomArrayAdapter extends ArrayAdapter<Contact> {

    public CustomArrayAdapter(@NonNull Context context, List<Contact> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name_field);
        TextView number = (TextView) convertView.findViewById(R.id.number_field);
        String nameSurName = contact.getName() == null ? "Unknown" : contact.getName();
        name.setText(nameSurName);
        number.setText(contact.getNumber() == null ? "Unknown" : contact.getNumber());
        return convertView;
    }
}
