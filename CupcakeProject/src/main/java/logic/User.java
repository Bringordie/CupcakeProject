package logic;

/**
 * @author Bringordie - Frederik Braagaard
 */
public class User {

    private static String username;
    private String password;
    private double balance;
    private String email;
    private String name;
    private Shoppingcart shoppingcart;

    public Shoppingcart getShoppingcart() {
        return shoppingcart;
    }

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
