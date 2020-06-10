package com.example.dostawa;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.andremion.counterfab.CounterFab;
import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.dostawa.EventBus.CounterCartEvent;
import com.example.dostawa.Model.Common;
import com.example.dostawa.Model.Food;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class FoodDetailsFragment extends Fragment {
    private Unbinder unbinder;
    private android.app.AlertDialog waitingDialog;
    private CartDataSource cartDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.img_food)
    ImageView img_food;
    @BindView(R.id.btn_cart)
    CounterFab btnCart;
    @BindView(R.id.food_name)
    TextView food_name;
    @BindView(R.id.food_description)
    TextView food_description;
    @BindView(R.id.txt_food_price)
    TextView food_price;
    @BindView(R.id.number_button)
    ElegantNumberButton numberButton;

    @OnClick(R.id.btn_cart)
    void onCartItemAdd(){
        CartItem cartItem = new CartItem();
        cartItem.setFoodId(Common.foodSelected.getMenuId());
        cartItem.setFoodName(Common.foodSelected.getName());
        cartItem.setFoodPrice(Common.foodSelected.getPrice());
        cartItem.setFoodImage(Common.foodSelected.getImage());
        cartItem.setFoodQuantity(Integer.parseInt(numberButton.getNumber()));

        cartDataSource.getItemWithAllOptionsInCart(cartItem.getFoodId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CartItem>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CartItem cartItemFromDB) {
                        if (cartItemFromDB.equals(cartItem)) {
                            cartItemFromDB.setFoodPrice(cartItem.getFoodPrice());
                            cartItemFromDB.setFoodQuantity(cartItem.getFoodQuantity() + cartItem.getFoodQuantity());

                            cartDataSource.updateCart(cartItemFromDB)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new SingleObserver<Integer>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onSuccess(Integer integer) {
                                            Toast.makeText(getContext(), "Koszyk zaktualizowany", Toast.LENGTH_SHORT).show();
                                            EventBus.getDefault().postSticky(new CounterCartEvent(true));
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Toast.makeText(getContext(), "[UPDATE CART]"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                        else {
                            compositeDisposable.add(cartDataSource.insertOrReplaceAll(cartItem)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(()->{
                                        Toast.makeText(getContext(), "Dodano do koszyka", Toast.LENGTH_SHORT).show();
                                        EventBus.getDefault().postSticky(new CounterCartEvent(true));
                                    }, throwable -> {
                                        Toast.makeText(getContext(), "[UPDATE CART]"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    }));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage().contains("empty")){
                            compositeDisposable.add(cartDataSource.insertOrReplaceAll(cartItem)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(()->{
                                        Toast.makeText(getContext(), "Dodano do koszyka", Toast.LENGTH_SHORT).show();
                                        EventBus.getDefault().postSticky(new CounterCartEvent(true));
                                    }, throwable -> {
                                        Toast.makeText(getContext(), "[UPDATE CART]"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    }));
                        } else{
                            Toast.makeText(getContext(), "[GET CART]"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                //OLD
//                .subscribe(() -> {
//                            Toast.makeText(getContext(), "Add to Cart success from FoodDetails", Toast.LENGTH_SHORT).show();
//                            EventBus.getDefault().postSticky(new CounterCartEvent(true));
//                        },
//                        throwable -> {
//                            Toast.makeText(getContext(), "[CART ERROR]"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
//                        }));
//                    @Override
//                    public void onSuccess(CartItem cartItemFromDB) {
//                        if (cartItemFromDB.equals(cartItem)){
//                            cartItemFromDB.setFoodPrice(cartItem.getFoodPrice());
//                            cartItemFromDB.setFoodQuantity(cartItem.getFoodQuantity() + cartItem.getFoodQuantity());
//
//                            cartDataSource.updateCart(cartItemFromDB)
//                                    .subscribeOn(Schedulers.io())
//                                    .observeOn(AndroidSchedulers.mainThread())
//                                    .subscribe(new SingleObserver<Integer>() {
//                                        @Override
//                                        public void onSubscribe(Disposable d) {
//
//                                        }
//                                        @Override
//                                        public void onSuccess(Integer integer) {
//                                            Toast.makeText(getContext(), "Update Cart success", Toast.LENGTH_SHORT).show();
//                                            EventBus.getDefault().postSticky(new CounterCartEvent(true));
//                                        }
//
//                                        @Override
//                                        public void onError(Throwable e) {
//                                            Toast.makeText(getContext(), "[UPDATE CART]"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//                        } else{
//                            compositeDisposable.add(cartDataSource.insertOrReplaceAll(cartItem)
//                                    .subscribeOn(Schedulers.io())
//                                    .observeOn(AndroidSchedulers.mainThread())
//                                    .subscribe(()-> {
//                                        Toast.makeText(getContext(), "Add to Cart success", Toast.LENGTH_SHORT).show();
//                                        EventBus.getDefault().postSticky(new CounterCartEvent(true));
//                                    }, throwable -> {
//                                        Toast.makeText(getContext(), "[CART ERROR]"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
//                                    }));
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (e.getMessage().contains("empty"))
//                        {
//                            compositeDisposable.add(cartDataSource.insertOrReplaceAll(cartItem)
//                                    .subscribeOn(Schedulers.io())
//                                    .observeOn(AndroidSchedulers.mainThread())
//                                    .subscribe(()-> {
//                                        Toast.makeText(getContext(), "Add to Cart success", Toast.LENGTH_SHORT).show();
//                                        EventBus.getDefault().postSticky(new CounterCartEvent(true));
//                                    }, throwable -> {
//                                        Toast.makeText(getContext(), "[CART ERROR]"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
//                                    }));
//                        } else {
//                            Toast.makeText(getContext(), "[GET CART]"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });
    }



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        FoodDetailsViewModel slideshowViewModel = ViewModelProviders.of(this).get(FoodDetailsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_food_details, container, false);
        unbinder = ButterKnife.bind(this, root);
        initViews();
//        cartDataSource = new LocalCartDataSource(CartDatabase.getInstance(getContext()).cartDAO());
        slideshowViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), food -> {
            displayInfo(food);
        });
        return root;

    }

    private void initViews() {
        cartDataSource = new LocalCartDataSource(CartDatabase.getInstance(getContext()).cartDAO());
    }

    private void displayInfo(Food food) {
        Glide.with(getContext()).load(food.getImage()).into(img_food);
        food_name.setText(new StringBuilder(food.getName()));
        food_description.setText(new StringBuilder(food.getDescription()));
        food_price.setText(new StringBuilder(String.valueOf(food.getPrice())));
        ((AppCompatActivity)getActivity())
                .getSupportActionBar()
                .setTitle(Common.foodSelected.getName());
    }
    private void calculateTotalPrice(){
        int totalPrice = Common.foodSelected.getPrice();
        totalPrice = totalPrice * (Integer.parseInt(numberButton.getNumber()));
        food_price.setText(new StringBuilder(String.valueOf(totalPrice)));

//        private displayPrice = totalPrice * (Integer.parseInt(numberButton.getNumber()));
//        displayPrice = Math.round(displayPrice*100.0/100.0);
//        food_price.setText(new StringBuilder("").append(Common.formatPrice(displayPrice)).toString());
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
