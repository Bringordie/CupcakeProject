package logic;


public class User {
    
    private static String username;
    private String password;
    private double balance;
    private String email;
    private String name;
    
    /**
     * @author Bringordie - Frederik Braagaard
    */
//    public User(String username, String password, String email, 
//            String name) {
//       this.username = username;
//       this.password = password;
//       this.email = email;
//       this.name = name;
//    }

//    public User() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public void setUsername(String username) {
        this.username = username;
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
