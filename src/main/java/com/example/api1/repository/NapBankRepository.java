package com.example.api1.repository;

import com.example.api1.entity.NapBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NapBankRepository extends JpaRepository<NapBank, Integer> {
}
