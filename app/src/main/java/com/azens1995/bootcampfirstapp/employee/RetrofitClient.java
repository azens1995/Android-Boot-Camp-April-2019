package com.azens1995.bootcampfirstapp.employee;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Azens Eklak on 20/4/19.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */
public class RetrofitClient {

    private static Retrofit INSTANCE;
    public static final String BASE_URL ="https://jsonplaceholder.typicode.com/";

    public static Retrofit getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }
}
