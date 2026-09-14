package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service  // このクラスが「裏側の処理(ビジネスロジック)」を担当するという目印
public class PetService {
    
    private Map<String, String> petDatabase;

    // 初期データの準備
    public PetService() {
        petDatabase = new HashMap<>();
        petDatabase.put("もふ", "ホーランドロップ");
        petDatabase.put("たま", "スコティッシュフォールド");
        petDatabase.put("ぽん", "サイベリアン");
    }

    // 検索処理
    public String findPet(String name) {
        if (petDatabase.containsKey(name)) {
            return petDatabase.get(name);
        } else {
            return "エラー:『" + name + "』は登録されていません。";
        }
    }

    //  登録処理
    public String registerPet(String name, String breed) {
        petDatabase.put(name, breed);
        return "成功：" + name + "(" + breed + "）を辞書に登録しました！"; 
    }
}
