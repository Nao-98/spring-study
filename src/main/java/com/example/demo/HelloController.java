package com.example.demo;

// ListとArrayListを使うための準備
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 「このクラスはweb APIの窓口ですよ」という目印
@RestController
public class HelloController {

    // 「ブラウザから /hello にGETリクエストがきたら、このメソッドを動かしてね」という目印
    @GetMapping("/hello")
    public String sayHello() {
        // ここで返した文字列が、そのままブラウザに表示されます
        return "Hello, Spring Boot! はじめてのAPIです。";
    }

    // 文字列ではなく、UserProfileクラスのインスタンスをそのまま返す
    @GetMapping("/profile")
    public UserProfile getUserProfile() {
        return new UserProfile("Javaエンジニアの卵", "バックエンド開発");
    }

    // ArrayListを使って複数人のリストを返す
    @GetMapping("/users")
    public List<UserProfile> getUsers() {
        List<UserProfile> users = new ArrayList<>();

        // リストにデータを追加していく
        users.add(new UserProfile("たま", "プロジェクトマネージャー"));
        users.add(new UserProfile("ぽん", "デザイナー"));
        users.add(new UserProfile("新人エンジニア", "バックエンド開発"));

        // まとめて返す
        return users;
    }
}