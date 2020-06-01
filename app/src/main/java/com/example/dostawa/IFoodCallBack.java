package com.example.dostawa;

import com.example.dostawa.Model.Food;

import java.util.List;

public interface IFoodCallBack {
    void onFoodLoadSuccess(List<Food> foods);
    void onFoodLoadFailed(String message);

}
