package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// ロギング用の部品をインポート
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 時間を測るための部品をインポート
import org.springframework.util.StopWatch;

@Service
@Transactional  // クラスにつけると、この中にあるすべてのメソッドがトランザクション管理される
public class PetService {

    private final PetRepository petRepository;

    // ログを出力するための「記録係(logger)」を準備
    private static final Logger logger = LoggerFactory.getLogger(PetService.class);

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
        // ストップウォッチを用意して計測スタート
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 処理が始まったことを記録。{} の部分に変数の中身が埋め込まれます。
        logger.info("🐾 新しいペットの登録処理を開始します。リクエストデータ - 名前: {}, 品種: {}", name, breed);
        
        try {
            // 魔法のメソッド save() でDBに保存！
            petRepository.save(new Pet(name, breed));

            // 処理が終わったのでストップウォッチを止める
            stopWatch.stop();

            // 成功したこと＆かかった時間を取得して記録
            logger.info("✅ DB保存完了 (名前: {}) - 処理時間: {} ms", name, stopWatch.getTotalTimeMillis());

            return name + "（" + breed + "）を登録しました！";
        } catch (Exception e) {
            // 万が一エラーが起きたら、詳細なエラー内容(e)とともに記録
            logger.error("❌ DB保存エラー (名前: {} - 失敗までの時間: {} ms", name, stopWatch.getTotalTimeMillis(), e);
            throw e;  // エラー自体は Spring Boot に報告して、トランザクションのロールバックを発動させる
        }

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