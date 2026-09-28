package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Owner {

    @Id
    private String name; // 飼い主の名前を主キーに

    // ▼ 「1人の飼い主に対し、複数のペットがいるよ」という設定（1対多）
    @OneToMany(mappedBy = "owner")
    @JsonIgnore // 【重要】データの無限ループ爆発を防ぐ魔法のアノテーション
    private List<Pet> pets;

    // 空のコンストラクタ（Spring Bootのお約束）
    public Owner() {}

    // データを入れる用のコンストラクタ
    public Owner(String name) {
        this.name = name;
    }

    // ゲッターとセッター
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Pet> getPets() { return pets; }
    public void setPets(List<Pet> pets) { this.pets = pets; }
}