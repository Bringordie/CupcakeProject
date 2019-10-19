package logic;


public class Topping {
    
    private final int id;
    private final String name;
    private String type = "Topping";
    private final double price;
    
    
    public Topping (String name, double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.type = type;
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
    
    
    
}
