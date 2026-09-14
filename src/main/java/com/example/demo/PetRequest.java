package com.example.demo;

public class PetRequest {
    private String name;
    private String breed;

    // Spring BootがJSONの中身をsetし、プログラム側で取り出す(Get)ためにGetterとSetterの両方が必要
    public String getName() {
        return name;   
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}