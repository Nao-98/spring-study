package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 画面に返すための空のMap(辞書)を用意
        Map errors = new HashMap<>();

        // パニックになったエラー情報(ex)から、発生したすべてのエラーを取り出してループ処理
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            // どの項目(nameなど)でエラーが起きたかを取り出す
            String fieldName = ((FieldError) error).getField();

            // どんなエラーメッセージ("名前は必須です" など)かを取り出す
            String errorMessage = error.getDefaultMessage();

            // 用意したMapに、エラー項目とメッセージのペアを追加する
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}