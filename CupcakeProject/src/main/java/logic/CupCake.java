package logic;

import java.util.ArrayList;


public class CupCake {

    static ArrayList<CupCake> CupCakeOrderHolder;
    
    public CupCake(Topping topping, Bottom bottom ) {
        CupCake cupcake = new CupCake(topping, bottom);
        LineItems lineitems = new LineItems(cupcake);
        
    }
    
}
