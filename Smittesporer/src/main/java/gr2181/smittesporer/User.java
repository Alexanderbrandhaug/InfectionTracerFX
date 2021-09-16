package gr2181.smittesporer;

public class User {

    private String name, email, password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
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
        User newUser = new User("tuva", "tuvaco@stud.ntnu.no", "ntnu");
        System.out.println(newUser.getEmail());
    }

}