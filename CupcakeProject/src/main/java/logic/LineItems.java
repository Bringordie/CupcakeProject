package logic;

import java.util.ArrayList;

public class LineItems {

    protected static ArrayList<CupCake> lineitems = new ArrayList();
    private CupCake cupcake;

    public void setCupCakeTempHolder(CupCake CupCakeOrderHolder) {
        //CupCake.CupCakeOrderHolder = lineitems;
        lineitems.add(CupCakeOrderHolder);
    }

    public static ArrayList<CupCake> getLineitems() {
        return lineitems;
    }

    LineItems(CupCake cupcake) {
        lineitems.add(cupcake);
    }



}
