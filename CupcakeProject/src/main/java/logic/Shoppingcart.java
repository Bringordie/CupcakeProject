package logic;


public class Shoppingcart {
    private static double totalprice;
    
    public Shoppingcart( double totalprice) {
                this.totalprice = totalprice;
            }
    
    // Price
public static void totalprice(double total) {
        Shoppingcart.totalprice = total;
    }

    public static double getTotalprice() {
        return totalprice;
    }

    public static void setTotalprice(double totalprice) {
        Shoppingcart.totalprice = totalprice;
    }
}
