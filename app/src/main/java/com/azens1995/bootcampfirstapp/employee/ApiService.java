package com.azens1995.bootcampfirstapp.employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Azens Eklak on 20/4/19.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */
public interface ApiService {

    @GET("users")
    Call<List<Employee>> getEmployees();
}
