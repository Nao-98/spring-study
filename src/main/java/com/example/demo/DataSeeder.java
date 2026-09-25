package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final PetRepository petRepository;

    public DataSeeder(PetRepository petRepository) {
        this.petRepository = petRepository;
    }
    
    // サーバーが起動した瞬間に１回だけ実行されるメソッド
    @Override
    public void run(String... args) throws Exception {

        // サーバーを再起動する度にデータが無限増殖しないよう、DBが空っぽの時だけ実行する
        if (petRepository.count() == 0) {
             
            // 魔法のメソッド save() を使って初期データを登録
            petRepository.save(new Pet("たま", "スコティッシュフォールド"));
            petRepository.save(new Pet("ぽん", "サイベリアン"));

            // ターミナルに成功メッセージを表示
            System.out.println("🌱 初期データ（たま・ぽん）をデータベースに登録しました！");
        }
    }
}
