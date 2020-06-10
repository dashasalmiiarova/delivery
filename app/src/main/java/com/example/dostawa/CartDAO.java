package com.example.dostawa;

import android.view.View;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dostawa.CartItem;
import com.example.dostawa.Model.Common;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
@Dao
public interface CartDAO {
    @Query("SELECT * FROM Cart")
    Flowable<List<CartItem>> getAllCart();
    @Query("SELECT COUNT(*) from Cart")
    Single<Integer> countItemInCart();
    @Query("SELECT SUM(foodPrice * foodQuantity) from Cart")
    Single<Integer> sumPrice();
    @Query("SELECT * FROM Cart WHERE foodId=:foodId")
    Single<CartItem> getItemInCart (int foodId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertOrReplaceAll(CartItem... cartItems);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    Single<Integer> updateCart(CartItem cart);
    @Delete
    Single<Integer> deleteCart(CartItem cart);

    @Query("SELECT * FROM Cart WHERE foodId=:foodId")
    Single<CartItem> getItemWithAllOptionsInCart(int foodId);

    @Query("DELETE FROM Cart")
    Single<Integer> cleanCart();
}
//    @Query("SELECT * FROM Cart WHERE uid=:uid")
//    Flowable<List<CartItem>> getAllCart(String uid);
//
//    @Query("SELECT COUNT(*) from Cart WHERE uid=:uid")
//    Single<Integer> countItemInCart (String uid);
//
//    @Query("SELECT SUM(foodPrice*foodQuantity)  FROM Cart")
//    Single<Long> sumPriceInCart ();
//
//    @Query("SELECT * FROM Cart WHERE foodId=:foodId")
//    Single<CartItem> getItemCart (String foodId);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    Completable insertOrReplaceAll(CartItem... cartItems);
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    Single<Integer> updateCartItems (CartItem cartItems);
//
//    @Delete
//    Single<Integer> deleteCartItem(CartItem cartItem);
//
//    @Query("DELETE FROM Cart WHERE uid=:uid")
//    Single<Integer> cleanCart (String uid);
//}

