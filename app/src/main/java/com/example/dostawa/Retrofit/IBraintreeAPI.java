package com.example.dostawa.Retrofit;

//import android.database.Observable;
import io.reactivex.Observable;
import com.example.dostawa.Model.BraintreeToken;
import com.example.dostawa.Model.BraintreeTransaction;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IBraintreeAPI {
    @GET("checkouts/new")
    Observable<BraintreeToken> getToken();

    @POST("checkouts")
    @FormUrlEncoded
    Observable<BraintreeTransaction> submitPayment(@Field("amount") String amount,
                                                   @Field("payment_method_nonce") String nonce);

}
