package com.example.dostawa.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AndroidException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dostawa.CartAdapter;
import com.example.dostawa.CartDataSource;
import com.example.dostawa.CartDatabase;
import com.example.dostawa.CartItem;
import com.example.dostawa.EventBus.HideFABCart;
import com.example.dostawa.EventBus.UpdateItemCart;
import com.example.dostawa.LocalCartDataSource;
import com.example.dostawa.Model.Common;
import com.example.dostawa.PaymentActivity;
import com.example.dostawa.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GalleryFragment extends Fragment {
//CartViewModel - shareViewModel
    private GalleryViewModel galleryViewModel;
    private Parcelable recyclerViewState;
    private CartDataSource cartDataSource;
    private Unbinder unbinder;
    @BindView(R.id.recycler_cart)
    RecyclerView recycler_cart;
    @BindView(R.id.txt_total_price)
    TextView txt_total_price;
    @BindView(R.id.group_place_holder)
    CardView group_place_holder;
    @BindView(R.id.txt_empty_cart)
    TextView txt_empty_cart;
    @BindView(R.id.btn_place_order)
    Button btn_place_order;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        unbinder = ButterKnife.bind(this, root);
        initViews();
        calculateTotalPrice();
        galleryViewModel.initCartDataSource(getContext());
        galleryViewModel.getMutableLiveDataItems().observe(getViewLifecycleOwner(), cartItems -> {
            if (cartItems == null || cartItems.isEmpty())
            {
                recycler_cart.setVisibility(View.GONE);
                group_place_holder.setVisibility(View.GONE);
                txt_empty_cart.setVisibility(View.VISIBLE);
            } else {
                recycler_cart.setVisibility(View.VISIBLE);
                group_place_holder.setVisibility(View.VISIBLE);
                txt_empty_cart.setVisibility(View.GONE);

                CartAdapter adapter = new CartAdapter(getContext(), cartItems);
                recycler_cart.setAdapter(adapter);
            }
        });
        ((AppCompatActivity)getActivity())
                .getSupportActionBar()
                .setTitle("Koszyk");

//        cartDataSource = new LocalCartDataSource(CartDatabase.getInstance(getContext()).cartDAO());

        return root;
    }

    private void initViews() {
        cartDataSource = new LocalCartDataSource(CartDatabase.getInstance(getContext()).cartDAO());

        EventBus.getDefault().postSticky(new HideFABCart(true));

        recycler_cart.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler_cart.setLayoutManager(layoutManager);
        recycler_cart.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        EventBus.getDefault().postSticky(new HideFABCart(false));
        galleryViewModel.onStop();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        super.onStop();
    }
    @OnClick(R.id.btn_place_order)
    void onButtonPlaceClick(){
        Intent intent = new Intent(getContext(), PaymentActivity.class);
        startActivity(intent);
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onUpdateItemCartEvent(UpdateItemCart event){
        if (event.getCartItem() != null){
            //getting state
            recyclerViewState = recycler_cart.getLayoutManager().onSaveInstanceState();
            cartDataSource.updateCart(event.getCartItem())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Integer>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(Integer integer) {
                            calculateTotalPrice();
                            recycler_cart.getLayoutManager().onRestoreInstanceState(recyclerViewState);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(getContext(), "[UPDATE CART]"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void calculateTotalPrice() {
        cartDataSource.sumPrice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        txt_total_price.setText(Common.formatPrice(integer));
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "[SUM CART]"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
