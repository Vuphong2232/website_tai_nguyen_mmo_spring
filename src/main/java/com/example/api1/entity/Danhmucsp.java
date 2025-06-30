package com.example.api1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "danh_muc_sp")
public class Danhmucsp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = true, length = 100)
    private String title;

    @Column(name = "toslu", nullable = false)
    private String toslu;

    @Column(name = "img", nullable = true)
    private String img;

    @Column(name = "status", nullable = true, length = 100)
    private String status;

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getToslu() {
        return toslu;
    }

    public void setToslu(String toslu) {
        this.toslu = toslu;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
