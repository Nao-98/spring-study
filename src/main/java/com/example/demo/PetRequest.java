package com.example.demo;

// 見張りのルールブックを読み込む
import jakarta.validation.constraints.NotBlank;

public class PetRequest {

    // nameの箱の上に「空っぽは絶対だめ！(NotBlank)という見張りを立たせる
    @NotBlank(message = "名前は必須です")
    private String name;

    // breedの箱の上にも同じ見張りを立たせる
    @NotBlank(message = "品種は必須です")
    private String breed;

    // Spring BootがJSONの中身をsetし、プログラム側で取り出す(Get)ためにGetterとSetterの両方が必要
    public String getName() {
        return name;   
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}