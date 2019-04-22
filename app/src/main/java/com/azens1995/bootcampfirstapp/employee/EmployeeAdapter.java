package com.azens1995.bootcampfirstapp.employee;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azens1995.bootcampfirstapp.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Azens Eklak on 20/4/19.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    private List<Employee> employeeList = Collections.emptyList();
    private Context context;

    public EmployeeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_employee_item, viewGroup, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int i) {
        Employee employee = employeeList.get(i);
        employeeViewHolder.eName.setText(employee.getName());
        employeeViewHolder.eAge.setText(employee.getUsername());
        employeeViewHolder.eCompany.setText(employee.getEmail());
        employeeViewHolder.eEyeColor.setText(employee.getWebsite());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public void addEmployees(List<Employee> employees){
        employeeList = employees;
        notifyDataSetChanged();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private TextView eName;
        private TextView eCompany;
        private TextView eAge;
        private TextView eEyeColor;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            eName = itemView.findViewById(R.id.employee_name);
            eCompany = itemView.findViewById(R.id.employee_company);
            eAge = itemView.findViewById(R.id.employee_age);
            eEyeColor = itemView.findViewById(R.id.employee_eye_color);
        }
    }
}
