package com.example.petcareapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewPetActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Pet> list;
    DBHelper dbHelper;
    PetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pet);

        gridView = findViewById(R.id.gridView);
        dbHelper = new DBHelper(this);

        loadData();

        // ✅ LONG CLICK DELETE (correct place)
        gridView.setOnItemLongClickListener((parent, view, position, id) -> {

            Pet pet = list.get(position);

            new android.app.AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setMessage("Delete this pet?")
                    .setPositiveButton("Yes", (dialog, which) -> {

                        dbHelper.deletePet(pet.name);

                        list.remove(position);
                        adapter.notifyDataSetChanged();

                        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();

            return true;
        });
    }

    private void loadData() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Pet", null);

        list = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                list.add(new Pet(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();

        adapter = new PetAdapter(this, list); // ✅ make global
        gridView.setAdapter(adapter);
    }
}