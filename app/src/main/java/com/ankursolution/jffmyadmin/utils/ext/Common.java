package com.ankursolution.jffmyadmin.utils.ext;

public class Common {

    public static String setPrice(String price)
    {
        return "₹ "+price;
    }

    public static String setTotalPrice(String price,String quantity)
    {
        double total =0;
        try {
            total = Double.parseDouble(price)*Integer.parseInt(quantity);
        }catch (Exception e){e.printStackTrace();}

        return "₹ "+total;

    }



}
