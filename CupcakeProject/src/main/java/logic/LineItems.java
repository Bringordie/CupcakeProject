package logic;

/**
 * @author Bringordie - Frederik Braagaard
 */
public class LineItems {

    private CupCake cupcake;
    private int amount;
    private double price;

    public LineItems(CupCake cupcake, int amount) {
        this.cupcake = cupcake;
        this.amount = amount;
        this.price = cupcake.price * amount;
    }
    
    public CupCake getCupcake() {
        return cupcake;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }
    
    
    
    
}
