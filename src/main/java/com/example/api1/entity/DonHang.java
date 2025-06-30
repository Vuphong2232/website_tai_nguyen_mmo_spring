package com.example.api1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lich_su_mua_hang")
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Khóa ngoại trỏ tới bảng Sanpham
    @ManyToOne
    @JoinColumn(name = "id_sp", nullable = false)
    private Sanpham sanpham;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "ma_gd", length = 100)
    private String maGiaoDich;

    @Column(name = "don_hang", columnDefinition = "LONGTEXT")
    private String noiDungDonHang;

    @Column(name = "gia", length = 100)
    private String gia;

    @Column(name = "time", length = 100)
    private String time;

    @Column(name = "status", length = 100)
    private String status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sanpham getSanpham() {
        return sanpham;
    }

    public void setSanpham(Sanpham sanpham) {
        this.sanpham = sanpham;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(String maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public String getNoiDungDonHang() {
        return noiDungDonHang;
    }

    public void setNoiDungDonHang(String noiDungDonHang) {
        this.noiDungDonHang = noiDungDonHang;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
