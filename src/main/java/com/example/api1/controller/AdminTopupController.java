package com.example.api1.controller;

import com.example.api1.entity.NapBank;
import com.example.api1.service.NapBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminTopupController {

    @Autowired
    private NapBankService napBankService;

    // Giao diện trang nạp
    @GetMapping("/topup")
    public String showTopupPage(Model model) {
        List<NapBank> bankList = napBankService.getAll();
        model.addAttribute("bankList", bankList);
        return "admin/topup";
    }

    // API: Lấy danh sách NapBank dạng JSON
    @GetMapping("/topup/list")
    @ResponseBody
    public ResponseEntity<List<NapBank>> getAllNapBanks() {
        List<NapBank> list = napBankService.getAll();
        return ResponseEntity.ok(list);
    }

    // API: Thêm mới NapBank
    @PostMapping("/topup/add")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addNapBank(@ModelAttribute NapBank napBank) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            napBankService.save(napBank);
            response.put("status", "success");
            response.put("msg", "Thêm tài khoản ngân hàng thành công");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("msg", "Lỗi khi thêm: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // API: Cập nhật NapBank
    @PostMapping("/topup/update/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateNapBank(@PathVariable("id") int id,
                                                             @ModelAttribute NapBank napBank) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            NapBank existing = napBankService.getById(id);
            if (existing == null) {
                response.put("status", "error");
                response.put("msg", "Tài khoản ngân hàng không tồn tại");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            napBank.setId(id); // Gán lại ID để cập nhật đúng bản ghi
            napBankService.save(napBank);
            response.put("status", "success");
            response.put("msg", "Cập nhật tài khoản ngân hàng thành công");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("msg", "Lỗi khi cập nhật: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // API: Xoá NapBank
    @DeleteMapping("/topup/delete/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteNapBank(@PathVariable("id") int id) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            NapBank existing = napBankService.getById(id);
            if (existing == null) {
                response.put("status", "error");
                response.put("msg", "Tài khoản không tồn tại hoặc đã bị xoá");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            napBankService.delete(id);
            response.put("status", "success");
            response.put("msg", "Xoá tài khoản ngân hàng thành công");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("msg", "Lỗi khi xoá: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
