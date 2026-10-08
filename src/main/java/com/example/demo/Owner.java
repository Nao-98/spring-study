package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Owner {

    @Id
    private String name; // 飼い主の名前を主キーに

    // ▼ 「1人の飼い主に対し、複数のペットがいるよ」という設定（1対多）
    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

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

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    //「この飼い主データがDBから削除される直前」に自動で呼ばれる魔法
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