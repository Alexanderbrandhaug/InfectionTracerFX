package infectiontracer.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CloseContact {

    private String name;
    private LocalDate date;
    private boolean infected;
    private String dateOfInfected;

    public CloseContact(String name, LocalDate date, boolean infected, String dateOfInfected) {
        this.name = name;
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

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;

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

  
}
