package com.example.api1.controller;

import com.example.api1.entity.Sanpham;

import com.example.api1.entity.User;
import com.example.api1.service.SanphamService;
import com.example.api1.service.UserService;
import com.example.api1.service.DanhmucspService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private SanphamService sanphamService;

    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        List<Sanpham> sanphamList = sanphamService.getAllSanpham();
        Map<Integer, List<String>> ttSpMap = new HashMap<>();
        Map<Integer, List<String>> listGtMap = new HashMap<>();
        for (Sanpham sp : sanphamList) {
            List<String> gtLines = sp.getListSp() == null
                    ? List.of()
                    : Arrays.asList(sp.getListSp().split("\\r?\\n"));
            listGtMap.put(sp.getId(), gtLines);
            List<String> ttLines = sp.getTtSp() == null
                    ? List.of()
                    : Arrays.asList(sp.getTtSp().split("\\r?\\n"));
            ttSpMap.put(sp.getId(), ttLines);
        }
        model.addAttribute("sanphamList", sanphamList);
        model.addAttribute("ttSpMap", ttSpMap);
        model.addAttribute("listGtMap", listGtMap);
        return "home";
    }

}
