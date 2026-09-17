package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// ▼ class ではなく interface（インターフェース）になっていることに注目！
@Repository
public interface PetRepository extends JpaRepository<Pet, String> {
    
    // 中身は「空っぽ」でOK！
    
}