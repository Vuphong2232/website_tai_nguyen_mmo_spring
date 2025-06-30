package com.example.api1.service;
import com.example.api1.entity.Danhmucsp;
import com.example.api1.entity.Sanpham;
import com.example.api1.repository.SanphamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SanphamService {
    @Autowired
    private SanphamRepository sanphamRepository;
    public List<Sanpham> getAllSanpham() {
        return sanphamRepository.findAll();
    }
    public Sanpham getSanphamById(int id) {
        return sanphamRepository.findById(id).orElse(null);
    }
    public void saveSanpham(Sanpham sanpham) {
        sanphamRepository.save(sanpham);
    }
    public void updateSanpham(Sanpham sanpham) {
        sanphamRepository.save(sanpham);
    }
    public void deleteSanpham(int id) {
        sanphamRepository.deleteById(id);
    }
    public List<Sanpham> findByDanhMucId(int id_dm) {
        return sanphamRepository.findByDanhMucSp_Id(id_dm); // nếu dùng @ManyToOne
    }
}