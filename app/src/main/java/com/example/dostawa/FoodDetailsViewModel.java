package com.example.dostawa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dostawa.Model.Common;
import com.example.dostawa.Model.Food;

public class FoodDetailsViewModel extends ViewModel {
    private MutableLiveData<Food> mutableLiveData;

    public FoodDetailsViewModel(){

    }
    public MutableLiveData<Food> getMutableLiveData() {
        if (mutableLiveData == null)
            mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(Common.foodSelected);
        return mutableLiveData;
    }
}
