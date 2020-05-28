package com.example.dostawa.Model;

public class Category {
    private String food_id_name;
    private int image;

    public Category() {
    }

    public Category(String food_id_name, int image) {
        this.food_id_name = food_id_name;
        this.image = image;
    }

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
}
