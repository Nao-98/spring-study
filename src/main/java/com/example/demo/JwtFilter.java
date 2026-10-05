package com.example.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final String SECRET_KEY = "my-super-secret-key";  // LoginControllerと同じ秘密のハンコ

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // リクエストのヘッダーから「Authorization」という項目を取り出す
        String header = request.getHeader("Authorization");

        // 「Bearer (トークン)」という形で送られてきているかチェック
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7); // "Bearer " の後ろの長い文字列だけを切り出す

            try {
                // トークンが本物か（秘密のハンコが一致するか）をチェック
                Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
                DecodedJWT jwt = JWT.require(algorithm).build().verify(token);

                // 本物なら、トークンの中身からユーザー名を取り出す
                String username = jwt.getClaim("user").asString();

                // 警備員（Spring Security）に「この人は認証済みですよ！」と伝える
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                // トークンが偽物または期限切れの場合は何もしない（そのまま403エラーになる）
            }
        }
        // 次の処理（または次の警備員）へバトンタッチ
        filterChain.doFilter(request, response);
    }
}