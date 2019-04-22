package com.azens1995.bootcampfirstapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.azens1995.bootcampfirstapp.employee.EmployeeActivity;
import com.azens1995.bootcampfirstapp.notes.NotesActivity;

import static com.azens1995.bootcampfirstapp.LoginActivity.LOGIN_PREF;
import static com.azens1995.bootcampfirstapp.LoginActivity.LOGIN_STATUS;

public class HomeActivity extends AppCompatActivity {
    Button btnNotes;
    Button btnNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnNotes = findViewById(R.id.btn_notes_home);
        btnNews = findViewById(R.id.btn_news_home);

        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });

        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EmployeeActivity.class);
                startActivity(intent);
            }
        });
    }

    // Menu inflation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    // handling click in the menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_logout:
                logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logoutUser() {
        // set the login status to false
        SharedPreferences sharedPref = getSharedPreferences(LOGIN_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(LOGIN_STATUS, false);
        editor.commit();

        // open the login page
        Intent logoutIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(logoutIntent);
        finish();
    }


}
