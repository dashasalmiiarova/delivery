package com.example.dostawa.EventBus;

import com.example.dostawa.CartDatabase;
import com.example.dostawa.CartItem;

public class UpdateItemCart {
    private CartItem cartItem;

    public UpdateItemCart(CartItem cartItem) {
        this.cartItem = cartItem;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }
}
