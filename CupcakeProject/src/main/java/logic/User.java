package logic;


public class User {
    
    private final String username;
    private final String password;
    private double balance;
    private final String email;
    private final String name;
    
    /**
     * @author Bringordie - Frederik Braagaard
    */
    public User(String username, String password, String email, 
            String name) {
       this.username = username;
       this.password = password;
       this.balance = balance;
       this.email = email;
       this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
}
