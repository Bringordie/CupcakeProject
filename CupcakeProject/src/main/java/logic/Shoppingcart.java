package logic;

import java.util.ArrayList;

/**
 * @author Bringordie - Frederik Braagaard
 */
public class Shoppingcart {

    private static double totalprice;
    private ArrayList<LineItems> fullshoppingcart = new ArrayList();
    private static ArrayList<Shoppingcart> allshoppingcarts = new ArrayList();
    private String username;

    public Shoppingcart() {
        this.totalprice = totalprice;
        this.fullshoppingcart = new ArrayList();
        this.username = username;
        allshoppingcarts.add(this);
    }
    
    public static void setTotalprice(double total) {
        Shoppingcart.totalprice =+ total;
    }
    
    public void addToShoppingCart(LineItems lineitem){
        fullshoppingcart.add(lineitem);
        
    }

    public static double getTotalprice() {
        return totalprice;
    }

    public ArrayList<LineItems> getFullshoppingcart() {
        return fullshoppingcart;
    }
    
    @Override
    public String toString() {
        String results = "+";
        for (LineItems lineItems : fullshoppingcart) {
        results += lineItems.toString();
    }
    return results;
        }
        
}
