package com.wordpress.lonelytripblog.customkeyboardwitht9.data;

/**
 * Contact class. Model in MVP pattern
 */

public class Contact {
    private String name;
    private String surName;
    private String number;

    public Contact(String name, String surName, String number) {
        this.name = name;
        this.surName = surName;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (getName() != null ? !getName().equals(contact.getName()) : contact.getName() != null)
            return false;
        if (getSurName() != null ? !getSurName().equals(contact.getSurName()) : contact.getSurName() != null)
            return false;
        return getNumber() != null ? getNumber().equals(contact.getNumber()) : contact.getNumber() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getSurName() != null ? getSurName().hashCode() : 0);
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        return result;
    }
}
