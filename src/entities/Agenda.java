package entities;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Agenda {

    private List<Contact> contacts = new ArrayList<>();
    private File contactsFile = new File("contacts.txt");

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    public void showContacts() {
        if (contactsFile.length() > 0) {
            try (BufferedReader br = new BufferedReader(new FileReader(contactsFile))) {
                String line = br.readLine();
                while (line != null) {
                    System.out.println(line.toString());
                    line = br.readLine();
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        } else {
            System.out.println("Empty");
        }
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(contactsFile, true))) {
            bw.write(contact.toString());
            bw.newLine();
            System.out.println("Contact '" + contact.getName() + "' created sucessfully");
            System.out.println();
        } catch (IOException e) {

            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public void deleteContact(String contactName) {

        String line;
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(contactsFile))) {
            while ((line = br.readLine()) != null) {
                if(line.contains("Name: "+ contactName))
                line = br.readLine();
                updatedLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(contactsFile))) {
            for (String newlines : updatedLines) {
                bw.write(newlines);
                bw.newLine();
            }
            System.out.println("Contact " + contactName + " deleted successfully.");
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }


    public void updateContact() {

    }

}
