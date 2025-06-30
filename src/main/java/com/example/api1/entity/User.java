package com.example.api1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = true, length = 100)
    private String username;

    @Column(name = "password", nullable = true, length = 100)
    private String password;

    @Column(name = "level", nullable = false)
    private int level;

    @Column(name = "money", nullable = true, length = 100)
    private String money;

    @Column(name = "tong_nap", nullable = true, length = 100)
    private String tongNap;

    @Column(name = "token", nullable = true)
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTongNap() {
        return tongNap;
    }

    public void setTongNap(String tongNap) {
        this.tongNap = tongNap;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
