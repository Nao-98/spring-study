package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// ▼【重要】「これはMySQLのテーブルになります！」という宣言
@Entity
public class Pet {

    // ▼【重要】「これが主キー（データを特定する一意のID）です！」という宣言
    @Id
    private String name;

    private String breed;

    // --- JPAのルールで、空のコンストラクタ（初期化メソッド）が必須です ---
    public Pet() {
    }

    // --- データを入れるためのコンストラクタ ---
    public Pet(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }

    // --- 以下はいつもの Getter / Setter です ---
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
}