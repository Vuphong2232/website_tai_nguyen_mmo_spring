package com.example.api1.controller;

import com.example.api1.entity.DonHang;
import com.example.api1.entity.NapBank;
import com.example.api1.repository.DonHangRepository;
import com.example.api1.repository.NapBankRepository;
import com.example.api1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AminHomeController {

    @Autowired
    private DonHangRepository donHangRepository;
    @Autowired
    private NapBankRepository napBankRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/dashboards")
    public String showDashboard(Model model) {
        List<DonHang> danhSachDon = donHangRepository.findAll();

        Map<String, Integer> doanhThuNgayTrongThang = new TreeMap<>();
        Map<String, Integer> doanhThuThangTrongNam = new TreeMap<>();

        LocalDateTime now = LocalDateTime.now();
        String thangHienTai = String.format("%02d", now.getMonthValue());
        String namHienTai = String.valueOf(now.getYear());

        int tongDoanhThu = 0;
        int soDonHang = 0;

        for (DonHang don : danhSachDon) {
            if (!"thanhcong".equalsIgnoreCase(don.getStatus())) continue;

            try {
                LocalDateTime date = LocalDateTime.parse(don.getTime().replace(" ", "T"));
                int tien = Integer.parseInt(don.getGia().replaceAll("[^\\d]", ""));

                tongDoanhThu += tien;
                soDonHang++;

                if (String.format("%02d", date.getMonthValue()).equals(thangHienTai)
                        && String.valueOf(date.getYear()).equals(namHienTai)) {
                    String keyDay = String.format("%02d", date.getDayOfMonth());
                    doanhThuNgayTrongThang.merge(keyDay, tien, Integer::sum);
                }

                if (String.valueOf(date.getYear()).equals(namHienTai)) {
                    String keyMonth = String.format("%02d", date.getMonthValue());
                    doanhThuThangTrongNam.merge(keyMonth, tien, Integer::sum);
                }

            } catch (Exception e) {
                System.out.println("❌ Lỗi đơn hàng ID " + don.getId() + ": " + e.getMessage());
            }
        }

        List<String> labelsNgay = new ArrayList<>(doanhThuNgayTrongThang.keySet());
        List<Integer> dataNgay = new ArrayList<>(doanhThuNgayTrongThang.values());
        List<Integer> dataLoiNhuan = dataNgay.stream()
                .map(tien -> (int) Math.round(tien * 0.2))
                .collect(Collectors.toList());

        int loiNhuan = (int) Math.round(tongDoanhThu * 0.2);
        int soThanhVien = userRepository.findAll().size();


        model.addAttribute("tongDoanhThu", tongDoanhThu);
        model.addAttribute("soDonHang", soDonHang);
        model.addAttribute("loiNhuan", loiNhuan);
        model.addAttribute("soThanhVien", soThanhVien);

        model.addAttribute("labelsNgay", labelsNgay);
        model.addAttribute("dataNgay", dataNgay);
        model.addAttribute("dataLoiNhuan", dataLoiNhuan);

        model.addAttribute("labelsThang", new ArrayList<>(doanhThuThangTrongNam.keySet()));
        model.addAttribute("dataThang", new ArrayList<>(doanhThuThangTrongNam.values()));

        return "admin/dashboards";
    }


}
