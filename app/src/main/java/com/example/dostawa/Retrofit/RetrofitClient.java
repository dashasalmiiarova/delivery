package com.example.dostawa.Retrofit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit instance;
    public static Retrofit getInstance() {

//        Pattern p = Pattern.compile("\\d+");
//        Matcher m = p.matcher("string1234more567string890");
//        while(m.find()){
//            System.out.println(m.group());
//        }

        if (instance == null)
            instance = new Retrofit.Builder()
                    .baseUrl("https://us-central1-paymentbraintree.cloudfunctions.net/widgets/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return instance;
    }
}
