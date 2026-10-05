package com.example.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
public class LoginController {
    // カードキーを偽造されないための暗号キー
    // ※実務ではコードに直接書かず、環境変数などで隠す※
    private final String SECRET_KEY = "my-super-secret-key";

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        // 本人確認(今回は固定のIDとパスワードでチェック)
        if ("admin".equals(request.getUsername()) && "password123".equals(request.getPassword())) {

            // 合言葉が合っていればカードキー(JWT)を作成
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            String token = JWT.create()
                    .withIssuer("my-spring-app")  // 発行者(このアプリが発行しましたという証明)
                    .withClaim("user", request.getUsername())  // 誰のカードキーか
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))  // 有効期限(1時間)
                    .sign(algorithm);  // 最後にハンコを押す
            
            // JSON形式でトークンを返す
            return Map.of("token", token);
        } else {
            // パスワードが違う場合はエラーで追い返す
            throw new IllegalArgumentException("IDまたはパスワードが違います！");
        }
    } 
}
