package com.example.dostawa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.andremion.counterfab.CounterFab;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import butterknife.BindView;

public class FoodDetailsActivity extends AppCompatActivity {

    @BindView(R.id.img_food)
    ImageView img_food;
    @BindView(R.id.btn_cart)
    CounterFab btnCart;
    @BindView(R.id.food_name)
    TextView food_name;
    @BindView(R.id.food_description)
    TextView food_description;
    @BindView(R.id.food_price)
    TextView food_price;
    @BindView(R.id.number_button)
    ElegantNumberButton numberButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
    }
}
