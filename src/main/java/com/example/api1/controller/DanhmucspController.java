package com.example.api1.controller;

import com.example.api1.entity.Danhmucsp;
import com.example.api1.service.DanhmucspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/danhmucsp")
public class DanhmucspController {
    @Autowired
    private DanhmucspService danhmucspService;
    @GetMapping("/list")
    public String getAllDanhmucsp(Model model) {
        List<Danhmucsp> danhMucSpList = danhmucspService.getAllDanhmucsp();
        model.addAttribute("danhmucspList", danhMucSpList);
        return "home";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("danhmucsp", new Danhmucsp());
        return "sanpham/add_categori";
    }
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addDanhmucsp(@ModelAttribute Danhmucsp danhmucsp) {
        Danhmucsp addDanhMuc = danhmucspService.saveDanhmucsp(danhmucsp);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("msg", "Thêm Danh Mục Thành Công");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/view_product/{id}")
    public String editDanhmucsp(@PathVariable("id") int id, Model model) {
        Danhmucsp danhmucsp = danhmucspService.getDanhmucspById(id);
        model.addAttribute("danhmucsp", danhmucsp);
        return "sanpham/edit_categori";
    }
    @PostMapping("/update/{id}")
    public String updateDanhmucsp(@PathVariable("id") int id, @ModelAttribute Danhmucsp danhmucsp) {
        danhmucsp.setId(id);
        danhmucspService.updateDanhmucsp(danhmucsp);
        return "redirect:/danhmucsp/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteDanhmucsp(@PathVariable("id") int id) {
        danhmucspService.deleteDanhmucsp(id);
        return "redirect:/danhmucsp/list";
    }
}
