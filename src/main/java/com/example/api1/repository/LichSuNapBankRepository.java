package com.example.api1.repository;

import com.example.api1.entity.LichSuNapBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LichSuNapBankRepository extends JpaRepository<LichSuNapBank, Integer> {
    List<LichSuNapBank> findByUsername(String username); // nếu muốn lọc theo user
}
