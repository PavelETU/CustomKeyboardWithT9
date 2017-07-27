package com.wordpress.lonelytripblog.customkeyboardwitht9.data;

import com.wordpress.lonelytripblog.customkeyboardwitht9.trie_data_structure.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fake contacts provider. Used in mock build variant.
 */

public class ContactsProvider implements ContactsProviderContract {
    private List<Contact> contacts;

    public ContactsProvider() {
        contacts = new ArrayList<>(Arrays.asList(
                new Contact("Dummy", "Name", "+792325"),
                new Contact("Old", "Friend", "93845"),
                new Contact("New", "Friend", "9857**23"),
                new Contact("Dusty", "Mr", "983"),
                new Contact("Without", "number", null),
                new Contact(null, null, null),
                new Contact(null, "Ms", "****"),
                new Contact("Here", "Mr", "62*"),
                new Contact("Brand", "New", "##856"),
                new Contact("Ah", "Bro", "8574985712345678"),
                new Contact("Bro", "Mr", "*++8574985712345678"),
                new Contact("Ah", "Bro", "45834"),
                new Contact("Howard", "Bro", "8574985712345678"),


                new Contact("Dummy2", "Name2", "+792325"),
                new Contact("SoOld2", "Friend2", "93845"),
                new Contact("New2", "Friend2", "9857**23"),
                new Contact("Dusty2", "Mr2", "983"),
                new Contact("Without2", "number", null),
                new Contact("JustName", null, null),
                new Contact(null, "Ms", "**23**"),
                new Contact("Here", "We", "62*"),
                new Contact("Brand", "New", "##8546546"),
                new Contact("Ah", "Bro2", "8574985712345678"),
                new Contact(null, "Mr", "*++8574985712345678"),
                new Contact("Ah", "Bro", "45846434"),
                new Contact("Howard", "Bro", "5678"),

                new Contact("Dummy2", "Name2", "+7923434625"),
                new Contact("Old2", "SoFriend2", "938463445"),
                new Contact("New2", "Friend2", "9456857**23"),
                new Contact("Dusty2", "Mr2", "98643"),
                new Contact("Without2", "number", "+++532"),
                new Contact("JustName", null, "845"),
                new Contact(null, "Ms", "**23*64*"),
                new Contact("Here", "We", "622*"),
                new Contact("Brand", "New", "##85465765446"),
                new Contact("Ah", "Bro2", "8574985712678"),
                new Contact(null, "Mr", "*++857498571234855678"),
                new Contact("Ah", "Bro", "4584643244"),
                new Contact("Howard", "Bro", "55433897453+#678"),

                new Contact("Dummy2", "Nahfdgme2", "+792325"),
                new Contact("Old2", "Frifdend2", "93845"),
                new Contact("New2", "Frifgend2", "9857**23"),
                new Contact("Dufdsgsty2", "Mfggr2", "983"),
                new Contact("Witfgdhout2", "numfgber", null),
                new Contact("JustNafdme", null, null),
                new Contact(null, "Mgdfs", "**23**"),
                new Contact("Hgere", "Wdfe", "62*"),
                new Contact("Brdand", "Nehw", "##8546546"),
                new Contact("fAh", "Brvo2", "8574985712345678"),
                new Contact(null, "Mfdsr", "*++8574985712345678"),
                new Contact("dsAh", "Bsro", "45846434"),
                new Contact("Hofdsgward", "Bro", "5678"),

                new Contact("Dumvfdmy2", "Name2", "+7923434625"),
                new Contact("Old2", "Friend2", "938463445"),
                new Contact("New2", "Friecvnd2", "9456857**23"),
                new Contact("Duxsty2", "Mr2", "98643"),
                new Contact("Wivthout2", "number", "+++532"),
                new Contact("JustNdame", null, "845"),
                new Contact(null, "Ms", "**2364*64*"),
                new Contact("Here", "We", "63422*"),
                new Contact("Brand", "New", "##8557465765446"),
                new Contact("Ah", "Bro2", "857498585712678"),
                new Contact(null, "Mr", "*++85749857124234855678"),
                new Contact("Ah", "Bro", "458464324984"),
                new Contact("Howard", "Bro", "5543324897453+#678"),

                new Contact("Wd", null, "984597438")));
    }

    @Override
    public List<Contact> provideContacts() {
        return contacts;
    }

    @Override
    public Trie provideTrie() {
        Trie trie = new Trie();
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.getName() != null) {
                trie.insertWord(contact.getName(), i);
            }
            if (contact.getSurName() != null) {
                trie.insertWord(contact.getSurName(), i);
            }
            if (contact.getNumber() != null) {
                trie.insertNumber(contact.getNumber(), i);
            }
        }
        return trie;
    }
}
