package com.example.dostawa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andremion.counterfab.CounterFab;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;
import com.example.dostawa.Callback.IRecyclerClickListener;
import com.example.dostawa.CartItem;
import com.example.dostawa.EventBus.CounterCartEvent;
import com.example.dostawa.EventBus.FoodClick;
import com.example.dostawa.EventBus.HideFABCart;
import com.example.dostawa.Model.Common;
import com.example.dostawa.Model.Food;
import com.example.dostawa.Model.UserModel;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.example.dostawa.Model.UserModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.observers.SubscriberCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolderN>{
    private Context context;
    private List<Food> foodModel;
    public CompositeDisposable compositeDisposable;
    private CartDataSource cartDataSource;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public void onStop(){
        compositeDisposable.clear();
    }

    public FoodAdapter(Context context, List<Food> foodModel) {
        this.context = context;
        this.foodModel = foodModel;
        this.compositeDisposable = new CompositeDisposable();
        this.cartDataSource = new LocalCartDataSource(CartDatabase.getInstance(context).cartDAO());
    }


    @NonNull
    @Override
    public MyViewHolderN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.menu_row, parent, false);
//        return new MyViewHolderN(view);
        return new MyViewHolderN(LayoutInflater.from(context)
                .inflate(R.layout.menu_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderN holder, int position) {

        Glide.with(context).load(foodModel.get(position).getImage()).into(holder.menu_image);
        holder.menu_title.setText(new StringBuilder("")
            .append(foodModel.get(position).getName()));
        holder.menu_price.setText(new StringBuilder("")
            .append(foodModel.get(position).getPrice()));

        //Event
        holder.setListener((view, pos) -> {
            Common.foodSelected = foodModel.get(pos);
            EventBus.getDefault().postSticky(new FoodClick(true, foodModel.get(pos)));
        });
        if (user != null){
            holder.img_cart.setOnClickListener(v -> {
                CartItem cartItem = new CartItem();
                cartItem.setFoodId(foodModel.get(position).getMenuId());
                cartItem.setFoodName(foodModel.get(position).getName());
                cartItem.setFoodPrice(foodModel.get(position).getPrice());
                cartItem.setFoodImage(foodModel.get(position).getImage());
                cartItem.setFoodQuantity(1);

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
                                                    Toast.makeText(context, "Koszyk zaktualizowany", Toast.LENGTH_SHORT).show();
                                                    EventBus.getDefault().postSticky(new CounterCartEvent(true));
                                                }

                                                @Override
                                                public void onError(Throwable e) {
                                                    Toast.makeText(context, "[UPDATE CART]"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                                else {
                                    compositeDisposable.add(cartDataSource.insertOrReplaceAll(cartItem)
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(()->{
                                                Toast.makeText(context, "Dodano do koszyka", Toast.LENGTH_SHORT).show();
                                                EventBus.getDefault().postSticky(new CounterCartEvent(true));
                                            }, throwable -> {
                                                Toast.makeText(context, "[UPDATE CART]"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
                                                Toast.makeText(context, "Dodano do koszyka", Toast.LENGTH_SHORT).show();
                                                EventBus.getDefault().postSticky(new CounterCartEvent(true));
                                            }, throwable -> {
                                                Toast.makeText(context, "[UPDATE CART]"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                            }));
                                } else{
                                    Toast.makeText(context, "[GET CART]"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                //OLD
//                    .subscribe(()-> {
//                        Toast.makeText(context, "Add to Cart success", Toast.LENGTH_SHORT).show();
//                        EventBus.getDefault().postSticky(new CounterCartEvent(true));
//                    },throwable -> {
//                    Toast.makeText(context, "[CART ERROR]"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
//                }));




//                .subscribe(() -> {
//                    Toast.makeText(context, "Add to Cart success", Toast.LENGTH_SHORT).show();
//                    EventBus.getDefault().postSticky(new CounterCartEvent(true));
//                },throwable -> {
//                    Toast.makeText(context, "[CART ERROR]"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
//                }));

//            cartDataSource.getItemWithAllOptionsInCart(cartItem.getFoodId())
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new SingleObserver<CartItem>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//
//                        @Override
//                        public void onSuccess(CartItem cartItemFromDB) {
//                            if (cartItemFromDB.equals(cartItem)){
//                                cartItemFromDB.setFoodPrice(cartItem.getFoodPrice());
//                                cartItemFromDB.setFoodQuantity(cartItem.getFoodQuantity() + cartItem.getFoodQuantity());
//
//                                cartDataSource.updateCart(cartItemFromDB)
//                                        .subscribeOn(Schedulers.io())
//                                        .observeOn(AndroidSchedulers.mainThread())
//                                        .subscribe(new SingleObserver<Integer>() {
//                                            @Override
//                                            public void onSubscribe(Disposable d) {
//
//                                            }
//
//                                            @Override
//                                            public void onSuccess(Integer integer) {
//                                                Toast.makeText(context, "Update Cart success", Toast.LENGTH_SHORT).show();
//                                                EventBus.getDefault().postSticky(new CounterCartEvent(true));
//                                            }
//
//                                            @Override
//                                            public void onError(Throwable e) {
//                                                Toast.makeText(context, "[UPDATE CART]"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                            }
//                                        });
//                            } else{
//                                compositeDisposable.add(cartDataSource.insertOrReplaceAll(cartItem)
//                                    .subscribeOn(Schedulers.io())
//                                    .observeOn(AndroidSchedulers.mainThread())
//                                    .subscribe(()-> {
//                                        Toast.makeText(context, "Add to Cart success", Toast.LENGTH_SHORT).show();
//                                        EventBus.getDefault().postSticky(new CounterCartEvent(true));
//                                    }, throwable -> {
//                                        Toast.makeText(context, "[CART ERROR]"+throwable.getMessage(), Toast)
//                                    }));
//                            }
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            Toast.makeText(context, "[GET CART]"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//            compositeDisposable.add(
//                    cartDataSource.insertOrReplaceAll(cartItem)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(() -> {
//                                Toast.makeText(context, "Add to Cart success", Toast.LENGTH_SHORT).show();
//                                EventBus.getDefault().postSticky(new CounterCartEvent(true));
//                            },
//                            throwable -> {
//                                Toast.makeText(context, "[CART ERROR]"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
//                            })
//            );
            });
        } else{
            holder.img_cart.setVisibility(View.GONE);
            Toast.makeText(context,"Zaloguj się", Toast.LENGTH_SHORT).show();

        }


//        holder.img_cart.setOnClickListener(v -> {
//            CartItem cartItem = new CartItem();
//            cartItem.setUid(Common.currentUser.getUid(user.getUid()));
//            cartItem.setUserEmail(Common.currentUser.getEmail(user.getEmail()));
////            cartItem.setUid(Common.currentUser.getUid());
////            cartItem.setUserEmail(Common.currentUser.getEmail());
//
//            cartItem.setFoodId(foodModel.get(position).getFoodId());
//            cartItem.setFoodName(foodModel.get(position).getName());
//            cartItem.setFoodImage(foodModel.get(position).getImage());
//            cartItem.setFoodPrice(Double.valueOf(String.valueOf(foodModel.get(position).getPrice())));
//            cartItem.setFoodQuantity(1);
//
//            compositeDisposable.add(cartDataSource.insertOrReplaceAll(cartItem)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(() -> {
//                    Toast.makeText(context, "Add to Cart success", Toast.LENGTH_SHORT).show();
//                    EventBus.getDefault().postSticky(new CounterCartEvent(true));
//                },throwable -> {
//                    Toast.makeText(context, "[CART ERROR]"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                        ));
//        });
    }


    @Override
    public int getItemCount() {
        return foodModel == null? 0: foodModel.size();
    }

    public class MyViewHolderN extends RecyclerView.ViewHolder implements View.OnClickListener {
        Unbinder unbinder;
        @BindView(R.id.menu_title)
        TextView menu_title;
        @BindView(R.id.menu_image)
        ImageView menu_image;
        @BindView(R.id.txt_food_price)
        TextView menu_price;
        @BindView(R.id.img_cart)
        ImageView img_cart;

        IRecyclerClickListener listener;

        public void setListener(IRecyclerClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolderN(@NonNull View itemView){
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClickListener(v, getAdapterPosition());
        }
    }
}

//public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {
//    Context context;
//    List<Food> foodList;
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        Unbinder unbinder;
//        @BindView(R.id.menu_title)
//        TextView txt_menu_title;
//        @BindView(R.id.menu_image)
//        ImageView img_food;
//
//        public MyViewHolder(@NonNull View itemView){
//            super(itemView);
//            unbinder = ButterKnife.bind(this,itemView);
//        }
//    }
//
//    public FoodAdapter(Context context, List<Food> foodList) {
//        this.context = context;
//        this.foodList = foodList;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new MyViewHolder(LayoutInflater.from(context)
//                .inflate(R.layout.menu_row,parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Glide.with(context).load(foodList.get(position).getImage())
//                .into(holder.img_food);
//        holder.txt_menu_title.setText(foodList.get(position).getName());
//    }
//
//    @Override
//    public int getItemCount() {
//        return foodList.size();
//    }
//
//
//}
