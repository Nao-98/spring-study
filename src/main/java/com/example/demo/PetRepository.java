package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
import java.util.List;

// ▼ class ではなく interface（インターフェース）になっている
public interface PetRepository extends JpaRepository<Pet, String> {
    
    // 「名前(Name)に、指定した文字が含まれる(Containing)ペットを探す」
    List<Pet> findByNameContaining(String keyword);
    
}