package logic;

import java.util.ArrayList;


public class LineItems {
    protected static ArrayList<CupCake> lineitems;
    
    public static void setCupCakeTempHolder(ArrayList<CupCake> CupCakeOrderHolder) {
        CupCake.CupCakeOrderHolder = lineitems;
    }

    LineItems(CupCake cupcake) {
        lineitems.add(cupcake);
    }
    
    
    
}
