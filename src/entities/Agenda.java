package entities;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Agenda {

    private List<Contact> contacts = new ArrayList<>();
    private File contactsFile = new File("contacts.txt");

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Scanner sc = new Scanner(System.in);


    public void showContacts() {

        boolean contactsList = false;

        try (BufferedReader br = new BufferedReader(new FileReader(contactsFile))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.contains("Name")) {

                    System.out.println(line);
                    contactsList = true;

                }
            }
        } catch (IOException e) {

            System.out.println("Error reading file: " + e.getMessage());

        }
        if (!contactsList) {

            System.out.println("Empty");

        }
    }

    public void addContact(Contact contact) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(contactsFile, true))) {

            bw.write(contact.toString());
            bw.newLine();
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

                if (line.contains("Name: " + contactName)) line = br.readLine();

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
            System.out.println();

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }


    public void updateContact(String findContact) {

        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(contactsFile))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.contains("Name: " + findContact) || line.contains("Phone Number: " + findContact)) {

                    System.out.println("Contact found:");
                    System.out.println(line);

                    System.out.println("What do you wish to update?");
                    System.out.println("1. Name");
                    System.out.println("2. Phone number");
                    int answer = sc.nextInt();
                    sc.nextLine();

                    switch (answer) {

                        case 1:
                            System.out.print("Enter the new name: ");
                            String newName = sc.nextLine();
                            line = line.replace("Name: " + findContact, "Name: " + newName);
                            break;

                        case 2:
                            System.out.print("Enter the new phone number: ");
                            String newPhoneNumber = sc.nextLine();
                            line = line.replace("Phone Number: " + findContact, "Phone Number: " + newPhoneNumber);
                            break;

                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                }
                updatedLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(contactsFile))) {

            for (String newLine : updatedLines) {

                bw.write(newLine);
                bw.newLine();
            }

            System.out.println("Contact updated successfully.");

        } catch (IOException e) {

            System.out.println("Error writing file: " + e.getMessage());

        }
    }
}
