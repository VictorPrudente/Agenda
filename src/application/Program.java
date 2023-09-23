package application;

import entities.Contact;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean agenda = true;
        File contacts = new File("contacts.txt");

        while (agenda) {
            try {
                System.out.println("Agenda Menu: ");
                System.out.println("Options: ");
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
                        System.out.println("All contacts");
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        break;
                    case 2:
                        System.out.println("NEW CONTACT: ");
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Phone number: ");
                        long phoneNumber = sc.nextLong();
                        System.out.print("Birthday: ");
                        Date birthDay = sdf.parse(sc.next());

                        Contact contact = new Contact(name, phoneNumber, birthDay);
                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(contacts))) {
                            bw.write(contact.toString());
                            System.out.println("Contact '" + name + "' created sucessfully");
                        } catch (IOException e) {
                            System.out.println("Error writing file: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Update a contact.");
                        break;
                    case 4:
                        System.out.println("Delete a contact.");
                        break;
                    case 5:
                        System.out.println("Exiting agenda.");
                        agenda = false;
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
