package com.example.grapeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor( Color.parseColor( "#673AB7" ));
    }

    public void makeRegister(View view){
        startActivity( new Intent(getApplicationContext(), RegisterActivity.class));
    }

    public void openLogin(View view){
        startActivity( new Intent(getApplicationContext(), LoginActivity.class));
    }
}
