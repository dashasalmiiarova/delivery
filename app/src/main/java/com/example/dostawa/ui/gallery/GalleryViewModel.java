package com.example.dostawa.ui.gallery;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dostawa.CartDataSource;
import com.example.dostawa.CartDatabase;
import com.example.dostawa.CartItem;
import com.example.dostawa.LocalCartDataSource;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GalleryViewModel extends ViewModel {
    private CompositeDisposable compositeDisposable;
    private CartDataSource cartDataSource;
    private MutableLiveData<List<CartItem>> mutableLiveDataItems;

    public GalleryViewModel() {
        compositeDisposable = new CompositeDisposable();
    }
    public void initCartDataSource(Context context){
        cartDataSource = new LocalCartDataSource(CartDatabase.getInstance(context).cartDAO());
    }
    public void onStop(){
        compositeDisposable.clear();
    }
    public MutableLiveData<List<CartItem>> getMutableLiveDataItems() {
        if (mutableLiveDataItems == null)
            mutableLiveDataItems = new MutableLiveData<>();
        getAllCartItems();
        return mutableLiveDataItems;
    }

    private void getAllCartItems() {
        compositeDisposable.add(cartDataSource.getAllCart()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(cartItems -> {
                mutableLiveDataItems.setValue(cartItems);
            }, throwable -> {
                mutableLiveDataItems.setValue(null);
            }));
    }
}