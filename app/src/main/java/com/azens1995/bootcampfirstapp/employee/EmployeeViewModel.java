package com.azens1995.bootcampfirstapp.employee;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.azens1995.bootcampfirstapp.notes.NotesDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Azens Eklak on 20/4/19.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */
public class EmployeeViewModel extends AndroidViewModel {
    private static final String TAG = "EmployeeViewModel";
    private MutableLiveData<List<Employee>> mAllEmployees = new MutableLiveData<>();
    private LiveData<List<Employee>> employeeList;
    private Retrofit retrofitClient;
    private ApiService apiService;
    private NotesDatabase notesDatabase;
    private EmployeeDao employeeDao;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        notesDatabase = NotesDatabase.getInstance(application);
        employeeDao = notesDatabase.employeeDao();
    }

    public LiveData<List<Employee>> getmAllEmployees() {
        getEmployeesFromNetwork();
        employeeList = employeeDao.getAllEmployees();
        return employeeList;
    }

    private void getEmployeesFromNetwork() {
        retrofitClient = RetrofitClient.getInstance();
        apiService = retrofitClient.create(ApiService.class);
        Call<List<Employee>> call = apiService.getEmployees();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+response.toString());
                    //mAllEmployees.setValue(response.body());
                    employeeDao.insertAllEmployees(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d(TAG, "onFailure: Network error");
            }
        });
    }
}
