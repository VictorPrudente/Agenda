package application;

import entities.Agenda;
import entities.Contact;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
                        try (BufferedReader br = new BufferedReader(new FileReader(contacts))) {
                            System.out.println();
                            String contact = br.readLine();
                            while (contact != null) {
                                System.out.println(contact);
                                contact = br.readLine();
                            }
                        } catch (IOException e) {
                            System.out.println("Reading error: " + e.getMessage());
                            System.out.println();
                        }
                        break;

                    case 2:
                        System.out.println();
                        System.out.println("NEW CONTACT: ");
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Phone number: ");
                        long phoneNumber = sc.nextLong();
                        System.out.print("Birthday: ");
                        Date birthDay = sdf.parse(sc.next());

                        Contact contact = new Contact(name, phoneNumber, birthDay);
                        agenda.addContact(contact);
                        break;

                    case 3:

                        System.out.println("Update a contact.");
                        break;

                    case 4:
                        System.out.println();
                        System.out.println("Enter the name of the contact to delete it: ");
                        String deletionName = sc.nextLine();
                        agenda.deleteContact(deletionName);
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
                System.out.println("Unextected error." + e.getMessage());
            } catch (ParseException e) {
                System.out.println("Invalid format: " + e.getMessage());
            }
        }
    }
}
