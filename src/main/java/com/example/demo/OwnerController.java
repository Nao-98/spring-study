package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    
    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    // 飼い主の登録 (POST /owners?name=荒木)
    @PostMapping
    public String createOwner(@RequestParam String name) {
        if (ownerRepository.existsById(name)) {
            throw new IllegalArgumentException("すでに登録されている飼い主です: " + name);
        }
        ownerRepository.save(new Owner(name));
        return name + "さんを飼い主として登録しました！";
    }

    // 飼い主の一覧取得 (GET /owners)
    @GetMapping
    public List getAllOwners() {
        // JPAのリレーションにより、飼い主データの中に自動的にペットデータが入れ子になる
        return ownerRepository.findAll();
    }
}
