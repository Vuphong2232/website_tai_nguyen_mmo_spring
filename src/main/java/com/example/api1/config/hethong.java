package com.example.api1.config;
import ch.qos.logback.core.util.StringUtil;
import com.example.api1.entity.Danhmucsp;
import com.example.api1.entity.User;
import com.example.api1.service.DanhmucspService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import java.util.List;

@ControllerAdvice
public class hethong {
    @Autowired
    private DanhmucspService danhmucspService;
    @ModelAttribute

    public void addGlobalAttributes(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("money", user.getMoney());
            model.addAttribute("tongNap", user.getTongNap());
            model.addAttribute("id_user", user.getId());
            model.addAttribute("level", user.getLevel());
        }
        List<Danhmucsp> danhMucSpList = danhmucspService.getAllDanhmucsp();
        model.addAttribute("danhmucspList", danhMucSpList);
    }

}
