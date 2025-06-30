package com.example.api1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nap_bank")
public class NapBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String ctk;

    @Column(length = 100)
    private String stk;

    @Column(name = "ngang_hang", length = 100)
    private String ngangHang;

    @Column(name = "min_nap")
    private int minNap;

    @Column(length = 100)
    private String status;

    // Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCtk() {
        return ctk;
    }

    public void setCtk(String ctk) {
        this.ctk = ctk;
    }

    public String getStk() {
        return stk;
    }

    public void setStk(String stk) {
        this.stk = stk;
    }

    public String getNgangHang() {
        return ngangHang;
    }

    public void setNgangHang(String ngangHang) {
        this.ngangHang = ngangHang;
    }

    public int getMinNap() {
        return minNap;
    }

    public void setMinNap(int minNap) {
        this.minNap = minNap;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
