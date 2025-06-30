package com.example.api1.service;

import com.example.api1.entity.NapBank;
import com.example.api1.repository.NapBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NapBankService {

    @Autowired
    private NapBankRepository napBankRepository;

    public List<NapBank> getAll() {
        return napBankRepository.findAll();
    }

    public NapBank getById(int id) {
        return napBankRepository.findById(id).orElse(null);
    }

    public NapBank save(NapBank bank) {
        return napBankRepository.save(bank);
    }

    public void delete(int id) {
        napBankRepository.deleteById(id);
    }
}
