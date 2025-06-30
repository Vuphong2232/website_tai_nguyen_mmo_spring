package com.example.api1.service;

import com.example.api1.entity.LichSuNapBank;
import com.example.api1.repository.LichSuNapBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LichSuNapBankService {

    @Autowired
    private LichSuNapBankRepository napBankRepository;

    // ✅ Lấy toàn bộ danh sách
    public List<LichSuNapBank> getAll() {
        return napBankRepository.findAll();
    }

    // ✅ Thêm mới 1 bản ghi nạp
    public LichSuNapBank addNap(LichSuNapBank nap) {
        return napBankRepository.save(nap);
    }

    // (Tùy chọn) Lấy theo username
    public List<LichSuNapBank> getByUsername(String username) {
        return napBankRepository.findByUsername(username);
    }
}
