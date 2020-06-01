package com.example.dostawa.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dostawa.IFoodCallBack;
import com.example.dostawa.Model.Common;
import com.example.dostawa.Model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel implements IFoodCallBack {

    private MutableLiveData<List<Food>> foodLiveDataLists;
    private MutableLiveData<String> messageError;
    private IFoodCallBack foodCallBack;

    public HomeViewModel(){
        foodCallBack = this;
    }
    public MutableLiveData<List<Food>> getFood(){
        if (foodLiveDataLists == null)
        {
            foodLiveDataLists = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadFood();
        }
        return foodLiveDataLists;
    }

    private void loadFood() {
        List<Food> tempList = new ArrayList<>();
        DatabaseReference foodRef = FirebaseDatabase.getInstance().getReference(Common.FOOD_CATEGORY_REF);
        foodRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()){
                    Food model = itemSnapshot.getValue(Food.class);
                    tempList.add(model);
                }
                foodCallBack.onFoodLoadSuccess(tempList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                foodCallBack.onFoodLoadFailed(databaseError.getMessage());
            }
        });

    }

    public MutableLiveData<String> getMessageError() {
        return messageError;
    }

    @Override
    public void onFoodLoadSuccess(List<Food> foods) {
        foodLiveDataLists.setValue(foods);
    }

    @Override
    public void onFoodLoadFailed(String message) {
        messageError.setValue(message);
    }
}
//    public MutableLiveData<List<Food>> getFoodLiveDataLists(){
//        if (foodLiveDataLists == null)
//            foodLiveDataLists = new MutableLiveData<>();
//        foodLiveDataLists.setValue();
//        return foodLiveDataLists;
//    }

//    public MutableLiveData<List<Food>> getFoodLists() {
//        if (foodLists == null){
//            foodLists = new MutableLiveData<>();
//            messageError = new MutableLiveData<>();
//            loadFoods();
//        }
//        return foodLists;
//    }
//
//    private void loadFoods() {
//
//        List<Food> tempList = new ArrayList<>();
//        DatabaseReference foodsRef = FirebaseDatabase.getInstance().getReference("Category");
//        foodsRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()){
//                    Food model = itemSnapshot.getValue(Food.class);
//                    tempList.add(model);
//                }
//                foodCallBack.onFoodLoadSuccess(tempList);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                foodCallBack.onFoodLoadFailed(databaseError.getMessage());
//            }
//        });
//
//    };
//
//    public MutableLiveData<String> getMessageError() {
//        return messageError;
//    }
//
//    @Override
//    public void onFoodLoadSuccess(List<Food> foods){
//        foodLists.setValue(foods);
//    }
//    @Override
//    public void onFoodLoadFailed(String message){
//        messageError.setValue(message);
//    }
