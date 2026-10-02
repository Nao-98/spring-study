package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.validation.annotation.Validated;

@RestController
//「http://localhost:3000」からのアクセスを特別に許可する許可証
@CrossOrigin(origins = "http://localhost:3000")
public class PetController {

    // 厨房(Service)を用意する
    private final PetService petService;

    // Spring Bootが自動的にServiceを探して、ここにセットしてくれる(DIという重要機能)
    public PetController(PetService petService) {
        this.petService = petService;
    }

    // ▼ 全件取得のAPI
    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    // 新しいペットを登録するPOSTメソッド(DTOとバリデーションを利用)
    @PostMapping
    public String registerPet(@Validated @RequestBody PetRequest request) {
        // DTO（PetRequest）から名前と品種を取り出して、Serviceに渡す
        return petService.registerPet(request.getName(), request.getBreed());
    }

    @PutMapping("/pets/{name}")
    public String updatePet(@PathVariable String name, @Valid @RequestBody PetRequest request) {
        // 名前はURLから、新しい品種はJSONデータから受け取ってServiceへ渡す
        return petService.updatePet(name, request.getBreed());
    }

    @DeleteMapping("/pets/{name}")
    public String deletePet(@PathVariable String name) {
        return petService.deletePet(name);
    }

    // ▼ URLの例: GET /pets/search?keyword=ま
    @GetMapping("/search")
    public List<Pet> searchPets(@RequestParam String keyword) {
        return petService.searchPets(keyword);
    }
}
