package com.example.api1.controller;

import com.example.api1.entity.Danhmucsp;
import com.example.api1.entity.Sanpham;
import com.example.api1.service.DanhmucspService;
import com.example.api1.service.SanphamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class SanphamController {

    @Autowired
    private SanphamService sanphamService;

    @Autowired
    private DanhmucspService danhmucspService; // ✅ đã sửa

    @GetMapping("/view_product/{id_dm}")
    public String viewByDanhMuc(@PathVariable("id_dm") int id_dm, Model model) {
        Danhmucsp danhmuc = danhmucspService.getDanhmucspById(id_dm);
        if (danhmuc == null) return "redirect:/home";

        List<Sanpham> spList = sanphamService.findByDanhMucId(id_dm);
        model.addAttribute("danhmuc", danhmuc);
        model.addAttribute("sanphamList", spList);
        Map<Integer, List<String>> ttSpMap = new HashMap<>();
        Map<Integer, List<String>> listGtMap = new HashMap<>();
        for (Sanpham sp : spList) {
            List<String> gtLines = sp.getListSp() == null
                    ? List.of()
                    : Arrays.asList(sp.getListSp().split("\\r?\\n"));
            listGtMap.put(sp.getId(), gtLines);
        }
        model.addAttribute("listGtMap", listGtMap);
        for (Sanpham sp : spList) {
            List<String> lines = sp.getTtSp() == null
                    ? List.of()
                    : Arrays.asList(sp.getTtSp().split("\\r?\\n"));
            ttSpMap.put(sp.getId(), lines);
        }
        model.addAttribute("ttSpMap", ttSpMap);
        return "product/view_product";
    }

}
