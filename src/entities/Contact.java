package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Contact {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String name;
    private String phoneNumber;
    private Date birthDate;


    public Contact(String name, String phoneNumber, Date birthDate) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    @Override
    public String toString() {

        return "Name: " + name +
                " | Phone Number: " + phoneNumber +
                " | Birthday: " + sdf.format(birthDate);
    }
}
