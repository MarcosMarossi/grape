package com.example.grapeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.grapeproject.Help.SettingsDB;
import com.example.grapeproject.Panel.StatusActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor( Color.parseColor( "#673AB7" ));

        verifyUser();
    }

    private void verifyUser() {

        auth = SettingsDB.getFireBaseAuth();
        if( auth.getCurrentUser() != null){
            startActivity(new Intent( getApplicationContext(), StatusActivity.class));
        }
    }

    public void makeRegister(View view){
        startActivity( new Intent(getApplicationContext(), RegisterActivity.class));
    }

    public void openLogin(View view){
        startActivity( new Intent(getApplicationContext(), LoginActivity.class));
    }
}
