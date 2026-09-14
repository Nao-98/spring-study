package com.example.demo;

// import java.util.HashMap;
// import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PetController {

    // 厨房(Service)を用意する
    private final PetService petService;

    // Spring Bootが自動的にServiceを探して、ここにセットしてくれる(DIという重要機能)
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/pets/{name}")
    public String getPetBreed(@PathVariable String name) {
        // 実際の検索処理はServiceにお任せ
        return petService.findPet(name);
    }

    // 新しいペットを登録するPOSTメソッド
    @PostMapping("/pets")
    public String addPet(@RequestBody PetRequest request) {
        // 実際の検索処理はServiceにお任せ
        return petService.registerPet(request.getName(), request.getBreed());
    }
}
