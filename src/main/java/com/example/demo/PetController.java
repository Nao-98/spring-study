package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {
    private Map<String, String> petDatabase;

    public PetController() {
        petDatabase = new HashMap<>();
        petDatabase.put("もふ", "ホーランドロップ");
        petDatabase.put("たま", "スコティッシュフォールド");
        petDatabase.put("ぽん", "サイベリアン");
    }

    @GetMapping("/pets/{name}")
    public String getPetBreed(@PathVariable String name) {
        if (petDatabase.containsKey(name)) {
            return petDatabase.get(name);
        } else {
            return "エラー:『" + name + "』は登録されていません。"; 
        }
    }
}
