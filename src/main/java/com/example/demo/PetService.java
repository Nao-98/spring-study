package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class PetService {

    // 思考1：貯蔵庫（Repository）を用意する
    private final PetRepository petRepository;

    // 思考2：Spring BootにRepositoryをセットしてもらう（DI）
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // 🐾 検索処理 (GET)
    public String findPet(String name) {
        // 思考3：Repositoryを使って、名前が「存在するか」チェックする
        if (petRepository.existByName(name)) {
            // 存在していれば、Repositoryから「取得」して返す
            return petRepository.findByName(name);
        } else {
            return "エラー:『" + name + "』は登録されていません。";
        }
    }

    // 🐾 登録処理 (POST)
    public String registerPet(String name, String breed) {
        // 思考4：Repositoryに「保存」をお願いする
        petRepository.save(name, breed);
        return "成功：" + name + "（" + breed + "）を辞書に登録しました！";
    }

    // 🐾 更新処理 (PUT)
    public String updatePet(String name, String newBreed) {
        // 思考5：Repositoryを使って、名前が「存在するか」チェックする
        if (petRepository.existByName(name)) {
            // 存在していれば、Repositoryに「保存（上書き）」をお願いする
            petRepository.save(name, newBreed);
            return "更新：" + name + "の品種を（" + newBreed + "）に変更しました！";
        } else {
            return "エラー:『" + name + "』は登録されていないため更新できません。";
        }
    }

    // 🐾 削除処理 (DELETE)
    public String deletePet(String name) {
        // 思考6：Repositoryを使って、名前が「存在するか」チェックする
        if (petRepository.existByName(name)) {
            // 存在していれば、Repositoryに「削除」をお願いする
            petRepository.deleteByName(name);
            return "削除：" + name + "のデータを削除しました。";
        } else {
            return "エラー:『" + name + "』は登録されていないため削除できません。";
        }
    }
}