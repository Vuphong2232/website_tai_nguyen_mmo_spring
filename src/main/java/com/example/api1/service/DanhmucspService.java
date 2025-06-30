package com.example.api1.service;

import com.example.api1.entity.Danhmucsp;
import com.example.api1.repository.DanhmucspRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhmucspService {

    @Autowired
    private DanhmucspRepository danhmucspRepository;
    public List<Danhmucsp> getAllDanhmucsp() {
        return danhmucspRepository.findAll();
    }
    public Danhmucsp getDanhmucspById(int id) {
        return danhmucspRepository.findById(id).orElse(null);
    }
    public Danhmucsp saveDanhmucsp(Danhmucsp danhmucsp) {
        danhmucspRepository.save(danhmucsp);
        return danhmucsp;
    }
    public void updateDanhmucsp(Danhmucsp danhmucsp) {
        danhmucspRepository.save(danhmucsp);
    }
    public void deleteDanhmucsp(int id) {
        danhmucspRepository.deleteById(id);
    }
}