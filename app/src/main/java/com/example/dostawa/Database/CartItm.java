//package com.example.dostawa.Database;
//
//import androidx.annotation.NonNull;
//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//
//@Entity(tableName = "Cart")
//public class CartItm {
//    @PrimaryKey
//    @NonNull
//    @ColumnInfo(name = "foodId")
//    private int foodId;
//    @ColumnInfo(name = "foodName")
//    private String foodName;
//    @ColumnInfo(name = "foodImage")
//    private String foodImage;
//    @ColumnInfo(name = "foodPrice")
//    private Double foodPrice;
//    @ColumnInfo(name = "foodQuantity")
//    private int foodQuantity;
//
//    public CartItm() {
//    }
//
//    public int getFoodId() {
//        return foodId;
//    }
//
//    public void setFoodId(int foodId) {
//        this.foodId = foodId;
//    }
//
//    public String getFoodName(String name) {
//        return foodName;
//    }
//
//    public void setFoodName(String foodName) {
//        this.foodName = foodName;
//    }
//
//    public String getFoodImage() {
//        return foodImage;
//    }
//
//    public void setFoodImage(String foodImage) {
//        this.foodImage = foodImage;
//    }
//
//    public Double getFoodPrice(String price) {
//        return foodPrice;
//    }
//
//    public void setFoodPrice(Double foodPrice) {
//        this.foodPrice = foodPrice;
//    }
//
//    public int getFoodQuantity() {
//        return foodQuantity;
//    }
//
//    public void setFoodQuantity(int foodQuantity) {
//        this.foodQuantity = foodQuantity;
//    }
//}
