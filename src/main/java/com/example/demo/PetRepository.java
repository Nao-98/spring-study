package com.example.demo;

import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository  // データベース操作を専門に担当するクラスの目印
public class PetRepository {
    private Map<String, String> petDatabase;

    public PetRepository() {
        petDatabase = new HashMap<>();
        petDatabase.put("もふ", "ホーランドロップ");
        petDatabase.put("たま", "スコティッシュフォールド");
        petDatabase.put("ぽん", "サイベリアン");
    }

    // データが存在するか確認する
    public boolean existByName(String name) {
        return petDatabase.containsKey(name);
    }

    // データを取得する
    public String findByName(String name) {
        return petDatabase.get(name);
    }

    // データを保存・更新する (POSTとPUTの両方で使える)
    public void save(String name, String breed) {
        petDatabase.put(name, breed);
    }

    // データを削除する
    public void deleteByName(String name) {
        petDatabase.remove(name);
    }
}