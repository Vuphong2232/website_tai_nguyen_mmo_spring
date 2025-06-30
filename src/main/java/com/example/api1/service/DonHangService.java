package com.example.api1.service;

import com.example.api1.entity.DonHang;
import com.example.api1.repository.DonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonHangService {

    @Autowired
    private DonHangRepository donHangRepository;

    public DonHang themDonHang(DonHang donHang) {
        return donHangRepository.save(donHang);
    }
    public List<DonHang> getDonHangByUsername(String username) {
        return donHangRepository.findByUsername(username);
    }
}
