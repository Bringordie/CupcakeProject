package logic;

import java.util.ArrayList;


public class CupCake {

    static ArrayList<CupCake> CupCakeOrderHolder;
    private Bottom bottom;
    private Topping topping;
    double price;
    
    public CupCake(Topping topping, Bottom bottom, double price) {
        //CupCake cupcake = new CupCake(topping, bottom, price);
        this.bottom = bottom;
        this.price = price;
        this.topping = topping;
        LineItems lineitems = new LineItems(this);
        lineitems.setCupCakeTempHolder(this);
        
    }
    
}
