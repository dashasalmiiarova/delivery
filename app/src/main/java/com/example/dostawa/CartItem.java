package com.example.dostawa;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;


@Entity(tableName = "Cart")
public class CartItem  {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "foodId")
    private int foodId;
    @ColumnInfo(name = "foodName")
    private String foodName;
    @ColumnInfo(name = "foodImage")
    private String foodImage;
    @ColumnInfo(name = "foodPrice")
    private int foodPrice;
    @ColumnInfo(name = "foodQuantity")
    private int foodQuantity;
//    @ColumnInfo (name = "userEmail")
//    private String userEmail;
//    @ColumnInfo (name = "uid")
//    private String uid;


    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof CartItem))
            return false;
        CartItem cartItem = (CartItem)obj;
        return Objects.equals(cartItem.getFoodId(), this.foodId);
    }

    //    public String getUserEmail() {
//        return userEmail;
//    }
//
//    public void setUserEmail(String userEmail) {
//        this.userEmail = userEmail;
//    }
//
//    public String getUid() {
//        return uid;
//    }
//
//    public void setUid(String uid) {
//        this.uid = uid;
//    }
}
