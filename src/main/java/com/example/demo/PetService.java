package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetRepository petRepository;

    // 魔法の杖（PetRepository）を持たせる（DI）
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // 🐾 1. 全件取得（GET）
    public List<Pet> getAllPets() {
        // ① 魔法のメソッド findAll() でDBから全件取得！
        return petRepository.findAll();
    }

    // 🐾 2. 登録（POST）
    public String registerPet(String name, String breed) {
        // 魔法のメソッド save() でDBに保存！
        petRepository.save(new Pet(name, breed));
        return name + "（" + breed + "）を登録しました！";
    }

    // 🐾 3. 更新（PUT）
    public String updatePet(String name, String breed) {
        // 魔法のメソッド existsById() で、その名前がDBにあるかチェック！
        if (petRepository.existsById(name)) {
            // ID（名前）が既に存在する場合、save() は「上書き保存（UPDATE）」になります！
            petRepository.save(new Pet(name, breed));
            return name + "の情報を更新しました！";
        }
        return name + "は見つかりませんでした。";
    }

    // 🐾 4. 削除（DELETE）
    public String deletePet(String name) {
        if (petRepository.existsById(name)) {
            // 魔法のメソッド deleteById() でDBから削除！
            petRepository.deleteById(name);
            return name + "を削除しました！";
        }
        return name + "は見つかりませんでした。";
    }

    // 🐾 5. 個別取得（GET）
    public String findPet(String name) {
        // 魔法のメソッド findById() でDBから特定の名前を検索！
        if (petRepository.existsById(name)) {
            // 見つかった場合はデータを取得して品種を返す（.get() で中身を取り出します）
            Pet pet = petRepository.findById(name).get();
            return pet.getBreed();
        }
        return name + "は見つかりませんでした。";
    }

    // ▼ 曖昧検索のメソッドを追加
    public List<Pet> searchPets(String keyword) {
        return petRepository.findByNameContaining(keyword);
    }
}