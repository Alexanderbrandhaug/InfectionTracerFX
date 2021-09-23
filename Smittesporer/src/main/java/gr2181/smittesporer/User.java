package gr2181.smittesporer;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class User {

    private String forname, lastname, email, password;

    public User() {

    }

    public void setForName(String forname){
    	Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher match = pattern.matcher(forname);
        if(match.find()){
           throw new IllegalArgumentException("Invalid forname");
        }
        forname.trim();
        this.forname = forname;
    }
    
    public String getForName() {
        return this.forname;
    }

    public void setLastName(String lastname) {
    	Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher match = pattern.matcher(lastname);
        if(match.find()){
           throw new IllegalArgumentException("Invalid lastname");
        }
        lastname.trim();
        this.lastname = lastname;
    }

    public String getLastName() {
        return this.lastname;
    }

    
    public void setEmail(String email) {
    	if (!email.contains("@") || !email.contains(".com")) {
    		throw new IllegalArgumentException("Invalid email");
    	}
    	this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
    	if(password.length()>=8) {
    		Pattern letter = Pattern.compile("[a-zA-z]");
            Pattern digit = Pattern.compile("[0-9]");
            Matcher hasLetter = letter.matcher(password);
            Matcher hasDigit = digit.matcher(password);
            
            if (hasLetter.find() && hasDigit.find()){
            	throw new IllegalArgumentException("Illegal password");
            }
            this.password = password;

        } else {
        	throw new IllegalArgumentException("Illegal password");
        }
    }

    public String getPassword() {
        return this.password;
    }
       

    public static void main(String[] args) {
        //User newUser = new User("tuva", "placeholder", "tuvaco@stud.ntnu.no", "ntnu");
    	User newUser = new User();
        newUser.setForName("lol");
        System.out.println(newUser.getForName());
    
    
    }

}