package com.example.api1.controller;

import com.example.api1.entity.LichSuNapBank;
import com.example.api1.service.LichSuNapBankService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPurchaseHistoryController {
    @Autowired
    private LichSuNapBankService napBankService;

    @GetMapping("/purchase_history")
    public String showLoginForm() {
        return "admin/purchase_history";
    }

    // ✅ Thêm mới hàm để hiển thị danh sách nạp bank
    @GetMapping("/purchase_history/list")
    @ResponseBody
    public List<LichSuNapBank> getNapBankApi() {
        return napBankService.getAll();
    }

}
