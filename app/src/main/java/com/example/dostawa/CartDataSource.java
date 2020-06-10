package com.example.dostawa;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dostawa.CartItem;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface CartDataSource {

    Flowable<List<CartItem>> getAllCart();

    Single<Integer> countItemInCart();

    Single<Integer> sumPrice();

    Single<CartItem> getItemInCart(int foodId);

    Completable insertOrReplaceAll(CartItem... cartItms);

    Single<Integer> updateCart(CartItem cart);

    Single<Integer> deleteCart(CartItem cart);

    Single<CartItem> getItemWithAllOptionsInCart(int foodId);

    Single<Integer> cleanCart();

}
//    Flowable<List<CartItem>> getAllCart(String uid);
//
//    Single<Integer> countItemInCart (String uid);
//
//    Single<Long> sumPriceInCart (String uid);
//
//    Single<CartItem> getItemCart (String foodId, String uid);
//
//    Completable insertOrReplaceAll(CartItem... cartItems);
//
//    Single<Integer> updateCartItems (CartItem cartItems);
//
//    Single<Integer> deleteCartItem(CartItem cartItem);
//
//    Single<Integer> cleanCart (String uid);
//}
