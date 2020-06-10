package com.example.dostawa;

import com.example.dostawa.CartItem;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class LocalCartDataSource implements CartDataSource {

    private CartDAO cartDAO;

    public LocalCartDataSource(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Override
    public Flowable<List<CartItem>> getAllCart() {
        return cartDAO.getAllCart();
    }

    @Override
    public Single<Integer> countItemInCart() {
         return cartDAO.countItemInCart();
    }

    @Override
    public Single<Integer> sumPrice() {
       return cartDAO.sumPrice();
    }

    @Override
    public Single<CartItem> getItemInCart(int foodId) {
        return cartDAO.getItemInCart(foodId);
    }


    @Override
    public Completable insertOrReplaceAll(CartItem... cartItms) {
        return cartDAO.insertOrReplaceAll(cartItms);
    }

    @Override
    public Single<Integer> updateCart(CartItem cart) {
        return cartDAO.updateCart(cart);
    }

    @Override
    public Single<Integer> deleteCart(CartItem cart) {
        return cartDAO.deleteCart(cart);
    }

    @Override
    public Single<CartItem> getItemWithAllOptionsInCart(int foodId) {
        return cartDAO.getItemWithAllOptionsInCart(foodId);
    }

    @Override
    public Single<Integer> cleanCart() {
        return cartDAO.cleanCart();
    }
}
//
//    private CartDAO cartDAO;
//
//    public LocalCartDataSource(CartDAO cartDAO) {
//        this.cartDAO = cartDAO;
//    }
//
//    @Override
//    public Flowable<List<CartItem>> getAllCart(String uid) {
//        return cartDAO.getAllCart(uid);
//    }
//
//    @Override
//    public Single<Integer> countItemInCart(String uid) {
//        return cartDAO.countItemInCart(uid);
//    }
//
//    @Override
//    public Single<Long> sumPriceInCart(String uid) {
//        return cartDAO.sumPriceInCart(uid);
//    }
//
//    @Override
//    public Single<CartItem> getItemCart(String foodId, String uid) {
//        return cartDAO.getItemCart(foodId, uid);
//    }
//
//    @Override
//    public Completable insertOrReplaceAll(CartItem... cartItems) {
//        return cartDAO.insertOrReplaceAll(cartItems);
//    }
//
//    @Override
//    public Single<Integer> updateCartItems(CartItem cartItems) {
//        return cartDAO.updateCartItems(cartItems);
//    }
//
//    @Override
//    public Single<Integer> deleteCartItem(CartItem cartItem) {
//        return cartDAO.deleteCartItem(cartItem);
//    }
//
//    @Override
//    public Single<Integer> cleanCart(String uid) {
//        return cartDAO.cleanCart(uid);
//    }
//}
