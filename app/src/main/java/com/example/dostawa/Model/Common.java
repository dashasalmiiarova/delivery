package com.example.dostawa.Model;

import com.example.dostawa.CartItem;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Common {
    public static final String FOOD_CATEGORY_REF = "Category";
    public static Food foodSelected;
    public static CartItem foodCartItem;
    public static final String USER_REFERENCE = "Users";
    public static UserModel currentUser;

    public static String formatPrice(int price){
        if (price != 0)
        {
//            DecimalFormat df = new DecimalFormat("#.##0.00");
//            df.setRoundingMode(RoundingMode.UP);
//            String finalPrice = new StringBuilder(df.format(price)).toString();
//            return finalPrice.replace(".", ",");
            return  new StringBuilder("").append(price).toString();
        }
        else{
            return "0,00";
        }
    }
}
