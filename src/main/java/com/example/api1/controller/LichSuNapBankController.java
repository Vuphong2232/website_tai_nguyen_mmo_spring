 package com.example.api1.controller;

import com.example.api1.entity.DonHang;
import com.example.api1.entity.LichSuNapBank;
import com.example.api1.service.LichSuNapBankService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lich-su-nap")
public class LichSuNapBankController {

    @Autowired
    private LichSuNapBankService napBankService;

    // ✅ Lấy danh sách hiển thị
    @GetMapping("/list")
    public String getList(Model model) {
        List<LichSuNapBank> list = napBankService.getAll();
        model.addAttribute("napList", list);
        return "admin/nap_list"; // cần tạo file này
    }
    @GetMapping("/lich-su-nap-tien")
    public String LichSuNapBanK(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) return "redirect:/users/login";
        List<LichSuNapBank> hisBankList = napBankService.getByUsername(username);
        int allbank = 0;
        allbank = hisBankList.size();
        model.addAttribute("allbank", allbank);
        model.addAttribute("hisBankList", hisBankList);
        return "bank/his_banks";
    }
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("nap", new LichSuNapBank());
        return "admin/nap_form";
    }

    // ✅ Xử lý thêm mới
    @PostMapping("/save")
    public String save(@ModelAttribute("nap") LichSuNapBank nap) {
        napBankService.addNap(nap);
        return "redirect:/lich-su-nap/list";
    }
}
