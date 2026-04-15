package com.example.petcareapp;

public class Pet {

    String name, species, breed, age, gender, image;

    public Pet(String name, String species, String breed,
               String age, String gender, String image) {

        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
        this.image = image;
    }
}