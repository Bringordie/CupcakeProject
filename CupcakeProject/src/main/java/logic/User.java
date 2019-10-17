package logic;


public class User {
    
    private final String username;
    private final String password;
    private double balance;
    private final String email;
    private final String name;
    private final Boolean role;
    
    public User(String username, String password, double balance, String email, 
            String name, Boolean role) {
       this.username = username;
       this.password = password;
       this.balance = balance;
       this.email = email;
       this.name = name;
       this.role = role;
       
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

    public Boolean getRole() {
        return role;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
}
