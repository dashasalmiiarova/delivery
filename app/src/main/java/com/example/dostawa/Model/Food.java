package com.example.dostawa.Model;

public class Food {
    private String Image, Name, Description;
    private int MenuId, Price;

    public Food() {
    }
    //    public Food(Long foodId) {
//        this.foodId = foodId;
//    }
//
//    public Food(String image, String name) {
//        Image = image;
//        Name = name;
////        foodId = foodId;
//    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getMenuId() {
        return MenuId;
    }

    public void setMenuId(int menuId) {
        MenuId = menuId;
    }
}
