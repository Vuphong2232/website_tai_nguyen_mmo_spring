package com.example.api1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "list_san_pham")
public class Sanpham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_dm", nullable = false)
    private Danhmucsp danhMucSp;

    @Column(name = "title", nullable = true, columnDefinition = "TEXT")
    private String title;

    @Column(name = "price", nullable = true, length = 100)
    private String price;

    @Column(name = "tt_sp", nullable = true, columnDefinition = "LONGTEXT")
    private String ttSp;

    @Column(name = "list_gt", nullable = true, columnDefinition = "TEXT")
    private String listGT;

    @Column(name = "ng_ban", nullable = true, length = 100)
    private String ngBan;

    @Column(name = "quoc_gia", nullable = true, length = 100)
    private String quocGia;

    @Column(name = "status", nullable = true, length = 100)
    private String status;

    // Getter bổ sung để dùng sp.id_dm trong view
    public Integer getId_dm() {
        return danhMucSp != null ? danhMucSp.getId() : null;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Danhmucsp getDanhMucSp() { return danhMucSp; }
    public void setDanhMucSp(Danhmucsp danhMucSp) { this.danhMucSp = danhMucSp; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getTtSp() { return ttSp; }
    public void setTtSp(String ttSp) { this.ttSp = ttSp; }

    public String getListSp() { return listGT; }
    public void setListSp(String listSp) { this.listGT = listSp; }

    public String getNgBan() { return ngBan; }
    public void setNgBan(String ngBan) { this.ngBan = ngBan; }

    public String getQuocGia() { return quocGia; }
    public void setQuocGia(String quocGia) { this.quocGia = quocGia; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
