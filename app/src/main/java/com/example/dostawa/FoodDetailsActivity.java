//package com.example.dostawa;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.Observer;
//
//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.andremion.counterfab.CounterFab;
//import com.bumptech.glide.Glide;
//import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
//import com.example.dostawa.Model.Common;
//import com.example.dostawa.Model.Food;
//
//import java.util.List;
//
//import butterknife.BindView;
//
//public class FoodDetailsActivity extends AppCompatActivity {
//    private MutableLiveData<Food> mutableLiveData;
//
//    @BindView(R.id.img_food)
//    ImageView img_food;
//    @BindView(R.id.btn_cart)
//    CounterFab btnCart;
//    @BindView(R.id.food_name)
//    TextView food_name;
//    @BindView(R.id.food_description)
//    TextView food_description;
//    @BindView(R.id.food_price)
//    TextView food_price;
//    @BindView(R.id.number_button)
//    ElegantNumberButton numberButton;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_food_details);
//
//        getMutableLiveData().observe(this, this::displayInfo);
//    }
//    public MutableLiveData<Food> getMutableLiveData(){
//        if (mutableLiveData == null){
//            mutableLiveData = new MutableLiveData<>();
//            mutableLiveData.setValue(Common.foodSelected);
//        }
//        return  mutableLiveData;
//    }
//    public void displayInfo(Food foodModel){
//        Glide.with(this).load(foodModel.getImage()).into(img_food);
//        food_name.setText(new StringBuilder((foodModel.getName())));
//
////        ((AppCompatActivity)getCallingActivity())
////                .getSupportActionBar();
//    }
//
//}
