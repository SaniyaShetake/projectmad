package com.example.petcareapp;

import android.content.Context;
import android.net.Uri;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

public class PetAdapter extends BaseAdapter {

    Context context;
    ArrayList<Pet> list;

    public PetAdapter(Context context, ArrayList<Pet> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.pet_item, parent, false);
        }

        ImageView img = view.findViewById(R.id.imgPet);
        TextView name = view.findViewById(R.id.tvName);

        Pet pet = list.get(i);

        name.setText(pet.name);

        try {
            Uri uri = Uri.parse(pet.image);
            img.setImageURI(uri);
        } catch (Exception e) {
            img.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        return view;
    }
}