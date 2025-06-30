package com.example.api1.repository;

import com.example.api1.entity.Danhmucsp;
import com.example.api1.entity.Sanpham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanphamRepository extends JpaRepository<Sanpham, Integer> {
    List<Sanpham> findByDanhMucSp_Id(int id_dm);  // Tìm theo khóa ngoại

}

