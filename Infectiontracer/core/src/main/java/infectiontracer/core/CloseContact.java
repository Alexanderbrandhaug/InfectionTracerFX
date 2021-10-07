package infectiontracer.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CloseContact {

    private User user;
    private LocalDate date;
    private boolean infected;
    private String dateOfInfected;

    public CloseContact(User user, LocalDate date, boolean infected, String dateOfInfected) {
        this.user = user;
        this.date = date;
        this.infected = infected;
        this.dateOfInfected = "";
    }

    public void setInfected(boolean infected) {
        this.infected = true;
    }

    public boolean getInfected() {
        return this.infected;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDateOfInfected() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LLLL-dd");
        String formattedString = today.format(formatter);
        this.dateOfInfected = formattedString;
    }

    public String getDateOfInfected() {
        return this.dateOfInfected;
    }

    public static void main(String[] args) {
       User user = new User("Alex", "test", "alex@gmail.com", "password321");
       LocalDate date = LocalDate.now();
       CloseContact closeContact = new CloseContact(user, date, false, "");
       closeContact.setInfected(true);
       closeContact.setDateOfInfected();
       System.out.println(closeContact.getInfected());
       System.out.println(closeContact.getDateOfInfected());
    }

}
