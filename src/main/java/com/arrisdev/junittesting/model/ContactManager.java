package com.arrisdev.junittesting.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactManager {
    Map<String, Contact> contactList = new ConcurrentHashMap<String, Contact>();

    public void addContact(String username, String email, String password, String phoneNumber){
        Contact contact = new Contact(username, email, password, phoneNumber);
        validateContact(contact);
        checkIfContactAlreadyExist(contact);
        contactList.put(generateKey(contact), contact);

    }

    public Collection<Contact> getAllContacts(){
        return contactList.values();
    }

    private void checkIfContactAlreadyExist(Contact contact){
        if (contactList.containsKey(generateKey(contact)))
            throw  new RuntimeException("Contact Already Exists.");
    }

    private void validateContact(Contact contact){
        contact.validateUsername();
        contact.validateEmail();
        contact.validatePassword();
        contact.validatePhoneNumber();
    }

    private String generateKey(Contact contact){
        return String.format("%s-%s", contact.getUsername(), contact.getEmail());
    }
}

