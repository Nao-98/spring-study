package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    // 作ったカードリーダー(JwtFilter)を読み込む
    private final JwtFilter jwtFilter;
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // API開発なので、Web画面用のセキュリティ（CSRF）を解除
            .csrf(csrf -> csrf.disable())

            // 警備員への「通していい場所 / ダメな場所」の指示
            .authorizeHttpRequests(auth -> auth
                // Swagger(説明書)の画面は誰でも見れるように許可（permitAll）
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                // 後で作る「カードキー発行窓口（/login）」も誰でもアクセスできるように許可
                .requestMatchers("/login").permitAll()
                // 本当のエラー（404や400）を隠さずに表示する
                .requestMatchers("/error").permitAll()
                // これ以外の全てのリクエストは「認証（カードキー）が必要」
                .anyRequest().authenticated()
            )
            // 通常の警備チェックの「前」に、自作のJWTカードリーダーを配置する
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
    }
}
