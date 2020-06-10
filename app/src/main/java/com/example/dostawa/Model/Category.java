package com.example.dostawa.Model;

public class Category {
    private String food_id_name;
    private int foodId;
    private int image;
    private int price;

    public Category() {
    }

    public Category(String food_id_name, int image, int price, int foodId) {
        this.food_id_name = food_id_name;
        this.image = image;
        this.price = price;
        this.foodId = foodId;
    }

    //    public Category(String food_id_name, int image) {
//        this.food_id_name = food_id_name;
//        this.image = image;
//    }

//    public Category(String fish, int img1) {
//    }

    public String getFood_id_name() {
        return food_id_name;
    }

    public void setFood_id_name(String food_id_name) {
        this.food_id_name = food_id_name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
