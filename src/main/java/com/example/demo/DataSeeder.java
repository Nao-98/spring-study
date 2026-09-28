package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;  // 飼い主用の窓口も呼ぶ

    public DataSeeder(PetRepository petRepository, OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }
    
    // サーバーが起動した瞬間に１回だけ実行されるメソッド
    @Override
    public void run(String... args) throws Exception {

        // 飼い主データが存在しない時だけ実行
        if (ownerRepository.count() == 0) {
            
            // まず「飼い主（荒木さん）」を作ってDBに保存
            Owner araki = new Owner("荒木");
            ownerRepository.save(araki);

            // 「たま」を作って、飼い主を荒木さんにセット（紐付け！）して保存
            Pet tama = new Pet("たま", "スコティッシュフォールド");
            tama.setOwner(araki);
            petRepository.save(tama);

            // 「ぽん」を作って、同じく飼い主をセットして保存
            Pet pon = new Pet("ぽん", "サイベリアン");
            pon.setOwner(araki);
            petRepository.save(pon);
            
            System.out.println("🌱 飼い主とペットの紐付けデータを登録しました！");
        }
    }
}