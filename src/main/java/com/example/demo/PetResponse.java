package com.example.demo;

public class PetResponse {
    private String name;
    private String breed;
    // ※createdAt や updatedAt は、あえて書かないことでフロントエンドから隠す

    // Entity（データベースのPet）を受け取って、この箱に移し替えるコンストラクタ
    public PetResponse(Pet pet) {
        this.name = pet.getName();
        this.breed = pet.getBreed();
    }

    // ゲッター（これがないとJSONにならない）
    public String getName() { return name; }
    public String getBreed() { return breed; }
}