package com.example.demo;

public class UserProfile {
    private String name;
    private String role;

    public UserProfile(String name, String role) {
        this.name = name;
        this.role = role;
    }

    // Spring BootがJSONに変換する際、このGetterを自動的に読み取る
    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
