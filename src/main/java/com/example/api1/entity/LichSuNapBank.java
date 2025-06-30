package com.example.api1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lich_su_nap_bank")
public class LichSuNapBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String username;

    @Column(name = "ngan_hang_nap", length = 100)
    private String nganHangNap;

    @Column(name = "ma_gd", length = 100)
    private String maGd;

    @Column(length = 100)
    private String time;

    private int amount;

    @Column(name = "thuc_nhan")
    private int thucNhan;

    @Column(length = 100)
    private String status;

    // Getters & Setters

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNganHangNap() {
        return nganHangNap;
    }

    public void setNganHangNap(String nganHangNap) {
        this.nganHangNap = nganHangNap;
    }

    public String getMaGd() {
        return maGd;
    }

    public void setMaGd(String maGd) {
        this.maGd = maGd;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getThucNhan() {
        return thucNhan;
    }

    public void setThucNhan(int thucNhan) {
        this.thucNhan = thucNhan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
