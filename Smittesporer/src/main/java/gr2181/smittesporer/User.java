package gr2181.smittesporer;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class User {

    private String forname, lastname, email, password;

    public User(String forname, String lastname, String email, String password) {

        this.forname = forname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }


    public String getForName() {
        return this.forname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getLastName() {
        return this.lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setForname(String forname){
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher match = pattern.matcher(forname);
        boolean a = match.find();
        if(a){
           System.out.println("PRINT DAWDWADWA");
        }else{
            this.forname = forname;
        }
       
    }

    public static void main(String[] args) {
        User newUser = new User("tuva", "placeholder", "tuvaco@stud.ntnu.no", "ntnu");
        newUser.setForname("21321");
        System.out.println(newUser.getForName());
    
    
    }

}