package entities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Agenda {

    private List<Contact> contacts = new ArrayList<>();
    private File contactsFile = new File("contacts.txt");

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public void addContact(Contact contact) {
        contacts.add(contact);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(contactsFile, true))) {
            bw.write(contact.toString());
            System.out.println("Contact '" + contact.getName() + "' created sucessfully");
            System.out.println();
        } catch (IOException e) {

            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public void deleteContact(String contactName) {
        List<String> updatedLines = new ArrayList<>();
        for (Contact contact : contacts) {
            if (!contact.getName().equals(contactName)) {
                updatedLines.add(contact.toString());
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(contactsFile))) {
            for (String line : updatedLines) {
            bw.write(line);
            }
            System.out.println("Contact " + contactName + " deleted successfully.");
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

    }

    public void updateContact() {

    }
}
