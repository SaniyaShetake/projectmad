package com.example.petcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAddPet);
        btnView = findViewById(R.id.btnViewPet);

        btnAdd.setOnClickListener(v ->
                startActivity(new Intent(this, AddPetActivity.class)));

        btnView.setOnClickListener(v ->
                startActivity(new Intent(this, ViewPetActivity.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();

        boolean loggedIn = getSharedPreferences("login", MODE_PRIVATE)
                .getBoolean("isLoggedIn", false);

        if (!loggedIn) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}