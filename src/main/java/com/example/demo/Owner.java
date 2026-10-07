package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import jakarta.persistence.PreRemove;

@Entity
public class Owner {

    @Id
    private String name; // 飼い主の名前を主キーに

    // ▼ 「1人の飼い主に対し、複数のペットがいるよ」という設定（1対多）
    @OneToMany(mappedBy = "owner")
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

    // ★追加：「この飼い主データがDBから削除される直前」に自動で呼ばれる魔法
    @PreRemove
    public void preRemove() {
        // もし紐づいているペットがいれば...
        if (pets != null) {
            for (Pet pet : pets) {
                // ペットの「飼い主情報」を空っぽ（null）にして、関係を断ち切る！
                pet.setOwner(null);
            }
        }
    }
}