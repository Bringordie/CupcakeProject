package logic;

import java.util.ArrayList;

/**
 * @author Bringordie - Frederik Braagaard
 */
public class Bottom {
    
    private final int id;
    private final String name;
    private final double price;
    private static ArrayList<Bottom> bottom = new ArrayList();
    
    public Bottom (String name, double price, int id) {
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
    
    public void setToppingsFromDB(ArrayList<Bottom> bottoms) {
        Bottom.bottom = bottoms;
    }

    public static ArrayList<Bottom> getToppingsFromDB() {
        return bottom;
    }
    
    
    
}
