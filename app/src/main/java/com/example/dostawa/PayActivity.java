package com.example.dostawa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.example.dostawa.Model.BraintreeToken;
import com.example.dostawa.Model.BraintreeTransaction;
import com.example.dostawa.Retrofit.IBraintreeAPI;
import com.example.dostawa.Retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PayActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1234;
    String token;

    Button btn_pay;
    EditText edt_payment;
    LinearLayout group_waiting, group_payment;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    IBraintreeAPI myApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        myApi = RetrofitClient.getInstance().create(IBraintreeAPI.class);
        group_payment = (LinearLayout) findViewById(R.id.payment_group);
        group_waiting = (LinearLayout) findViewById(R.id.waiting_group);
        btn_pay = (Button) findViewById(R.id.btn_pay);
        edt_payment = (EditText) findViewById(R.id.edit_amount);

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment();
            }
        });

        //Getting Token
        compositeDisposable.add((Disposable) myApi.getToken().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.functions.Consumer<BraintreeToken>() {
                    @Override
                    public void accept(BraintreeToken braintreeToken) throws Exception {
                        if (braintreeToken.isSuccess()) {
                            group_waiting.setVisibility(View.INVISIBLE);
                            group_payment.setVisibility(View.VISIBLE);
                            token = braintreeToken.getClientToken();
                        }
                    }
                }, new io.reactivex.functions.Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(PayActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));

    }
    @Override
    protected void onDestroy(){
        compositeDisposable.clear();
        super.onDestroy();
    }
    private void submitPayment(){
        DropInRequest dropInRequest = new DropInRequest().clientToken(token);
        startActivityForResult(dropInRequest.getIntent(this), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if (resultCode == RESULT_OK){
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                PaymentMethodNonce nonce = result.getPaymentMethodNonce();

                if (!TextUtils.isEmpty(edt_payment.getText().toString())){
                    String amount = edt_payment.getText().toString();
                    compositeDisposable.add(myApi
                            .submitPayment(amount, nonce.getNonce())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())

                            .subscribe(new Consumer<BraintreeTransaction>() {
                                @Override
                                public void accept(BraintreeTransaction braintreeTransaction) throws Exception {
                                    if (braintreeTransaction.isSuccess()){
                                        Toast.makeText(PayActivity.this, ""+braintreeTransaction.getTransaction().getId(), Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(PayActivity.this, "Payment failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Toast.makeText(PayActivity.this, ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }));
                }
            }
        }
    }
}
