package com.example.api1.controller;

import com.example.api1.entity.NapBank;
import com.example.api1.service.NapBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nap-bank")
public class NapBankController {
    @Autowired
    private NapBankService napBankService;
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("banks", napBankService.getAll());
        return "bank/bank_atm";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        NapBank bank = napBankService.getById(id);
        model.addAttribute("bank", bank);
        return "admin/bank_form";
    }
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("bank", new NapBank());
        return "admin/bank_form";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute NapBank bank) {
        napBankService.save(bank);
        return "redirect:/nap-bank/list";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        napBankService.delete(id);
        return "redirect:/nap-bank/list";
    }
}
