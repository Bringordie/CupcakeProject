package logic;

import java.util.ArrayList;

/**
 * @author Bringordie - Frederik Braagaard
 */
public class Topping {
    
    private final int id;
    private final String name;
    private final double price;
    private static ArrayList<Topping> topping = new ArrayList();
    
    
    public Topping (String name, double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    
    public static void setToppingsFromDB(ArrayList<Topping> toppings) {
        Topping.topping = toppings;
    }

    public static ArrayList<Topping> getToppingsFromDB() {
        return topping;
    }
    
    
    
}
