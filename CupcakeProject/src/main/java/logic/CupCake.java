package logic;

/**
 * @author Bringordie - Frederik Braagaard
 */
public class CupCake {

    private Bottom bottom;
    private Topping topping;
    double price;
    int quantity;
    
    public CupCake(int toppingid, int bottomid) {
        this.bottom = bottom;
        this.price = price;
        this.topping = topping;
    }

    @Override
    public String toString() {
        return "Bottom: "+bottom.getName()+" + "+"Topping: "+topping.getName()+" Price: "+(topping.getPrice()+bottom.getPrice());
    }
}
