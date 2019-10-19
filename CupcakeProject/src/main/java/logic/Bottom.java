package logic;


public class Bottom {
    
    private final int id;
    private final String name;
    private String type = "Bottom";
    private final double price;
    
    public Bottom (String name, double price, int id) {
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
