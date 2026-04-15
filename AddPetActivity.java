package com.example.petcareapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class AddPetActivity extends AppCompatActivity {

    EditText etName, etSpecies, etBreed, etAge, etGender;
    Button btnSave, btnUpload;

    String imageUri = "";
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        dbHelper = new DBHelper(this);

        etName = findViewById(R.id.etName);
        etSpecies = findViewById(R.id.etSpecies);
        etBreed = findViewById(R.id.etBreed);
        etAge = findViewById(R.id.etAge);
        etGender = findViewById(R.id.etGender);

        btnSave = findViewById(R.id.btnSave);
        btnUpload = findViewById(R.id.btnUpload);

        // Upload Image
        btnUpload.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            i.setType("image/*");
            startActivityForResult(i, 1);
        });

        // Save Data
        btnSave.setOnClickListener(v -> {

            if (imageUri.isEmpty()) {
                Toast.makeText(this, "Please upload image!", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put("name", etName.getText().toString());
            cv.put("species", etSpecies.getText().toString());
            cv.put("breed", etBreed.getText().toString());
            cv.put("age", etAge.getText().toString());
            cv.put("gender", etGender.getText().toString());
            cv.put("image", imageUri);

            db.insert("Pet", null, cv);

            Toast.makeText(this, "Pet Added", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            // Important permission
            getContentResolver().takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
            );

            imageUri = uri.toString();

            Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show();
        }
    }
}