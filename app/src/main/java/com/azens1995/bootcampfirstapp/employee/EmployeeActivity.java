package com.azens1995.bootcampfirstapp.employee;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.azens1995.bootcampfirstapp.R;

import java.util.List;

import retrofit2.Retrofit;

public class EmployeeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EmployeeViewModel employeeViewModel;
    private EmployeeAdapter employeeAdapter;

    private Retrofit retrofitClient;
    private ApiService apiService;

    private static final String TAG = "EmployeeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        recyclerView = findViewById(R.id.employee_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        employeeAdapter = new EmployeeAdapter(this);
        recyclerView.setAdapter(employeeAdapter);

        employeeViewModel.getmAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> employees) {
                employeeAdapter.addEmployees(employees);
                Log.d(TAG, "onChanged: "+employees.toString());
            }
        });

    }

    /*private void makeNetworkCall() {
        retrofitClient = RetrofitClient.getInstance();
        apiService = retrofitClient.create(ApiService.class);
        Call<List<Employee>> call = apiService.getEmployees();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+response.toString());
                    employeeAdapter.addEmployees(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d(TAG, "onFailure: Network error");
            }
        });
    }*/
}
