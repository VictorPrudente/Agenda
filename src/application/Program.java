package application;

import entities.Agenda;
import entities.Contact;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean execution = true;
        File contacts = new File("contacts.txt");
        Agenda agenda = new Agenda();
        while (execution) {
            try {

                System.out.println("Agenda Menu: ");
                System.out.println("1. Show all contacts.");
                System.out.println("2. Add new contact.");
                System.out.println("3. Update a contact.");
                System.out.println("4. Delete a contact.");
                System.out.println("5. Exit program.");
                System.out.print("Enter option: ");
                int option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        System.out.println();
                        System.out.println("Contacts: ");
                        agenda.showContacts();
                        System.out.println();
                        break;

                    case 2:
                        System.out.println();
                        System.out.println("NEW CONTACT: ");
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Phone number: ");
                        String phoneNumber = sc.nextLine();
                        System.out.print("Birthday: ");
                        Date birthDay = sdf.parse(sc.next());

                        Contact contact = new Contact(name, phoneNumber, birthDay);
                        agenda.addContact(contact);
                        System.out.println("Contact '" + contact.getName() + "' created sucessfully");

                        break;

                    case 3:

                        System.out.println("Update a contact: ");
                        System.out.println("Find your contact by name or phone number.");
                        System.out.println("1. Name");
                        System.out.println("2. Number");
                        System.out.print("Option: ");
                        int answer = sc.nextInt();
                        sc.nextLine();
                        if (answer == 1) {
                            System.out.println("Enter the name of your contact: ");
                        } else if (answer == 2) {
                            System.out.println("Enter the phone number of your contact: ");
                        } else {
                            System.out.println("Invalid input.");
                        }
                            String findContact = sc.nextLine();
                            agenda.updateContact(findContact);

                        break;

                    case 4:
                        System.out.println();
                        System.out.println("Enter the name of the contact to delete it: ");
                        String deletionName = sc.nextLine();
                        agenda.deleteContact(deletionName);
                        System.out.println("Contact " + deletionName + " deleted successfully.");
                        break;
                    case 5:

                        System.out.println("Exiting agenda.");
                        execution = false;
                        break;

                    default:

                        System.out.println("Invalid option.");
                        break;

                }
            } catch (RuntimeException e) {
                System.out.println("Unexpected error." + e.getMessage());
            } catch (ParseException e) {
                System.out.println("Invalid format: " + e.getMessage());
            }
        }
    }
}
