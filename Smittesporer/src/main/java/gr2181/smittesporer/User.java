package gr2181.smittesporer;

public class User {

    private String forname, lastname, email, password;

    public User(String forname, String lastname, String email, String password) {
        this.forname = forname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public void setForName(String forname) {
        this.forname = forname;
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

    public static void main(String[] args) {
        User newUser = new User("tuva", "placeholder", "tuvaco@stud.ntnu.no", "ntnu");
        System.out.println(newUser.getLastName());
    }

}