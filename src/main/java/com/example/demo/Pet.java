package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

// ▼【重要】「これはMySQLのテーブルになります！」という宣言
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Pet {

    // ▼【重要】「これが主キー（データを特定する一意のID）です！」という宣言
    @Id
    private String name;
    private String breed;

    // 「複数のペットは1人の飼い主に所属するよ」という設定(多対1)
    @ManyToOne
    @JoinColumn(name = "owner_name")
    @JsonIgnore
    private Owner owner;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // --- JPAのルールで、空のコンストラクタ（初期化メソッド）が必須です ---
    public Pet() {
    }

    // --- データを入れるためのコンストラクタ ---
    public Pet(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }

    // --- 以下はいつもの Getter / Setter ---
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    // Owner用のGetterとSetter
    public Owner getOwner() { return owner; }
    public void setOwner(Owner owner) { this.owner = owner; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}