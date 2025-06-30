package com.example.api1.controller;

import com.example.api1.entity.Danhmucsp;
import com.example.api1.service.DanhmucspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminCategoriesController {

    @Autowired
    private DanhmucspService danhmucspService;

    @GetMapping("/categories")
    public String showCategoriesPage(Model model) {
        List<Danhmucsp> danhMucSpList = danhmucspService.getAllDanhmucsp();
        model.addAttribute("danhmucspList", danhMucSpList);
        return "admin/categories";
    }

    @GetMapping("/list")
    public ResponseEntity<List<Danhmucsp>> getAllDanhmucsp() {
        List<Danhmucsp> danhMucSpList = danhmucspService.getAllDanhmucsp();
        return ResponseEntity.ok(danhMucSpList);
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateDanhmucsp(@PathVariable("id") int id, @ModelAttribute Danhmucsp danhmucsp) {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            // Lấy bản ghi cũ từ DB
            Danhmucsp existing = danhmucspService.getDanhmucspById(id);
            if (existing == null) {
                response.put("status", "error");
                response.put("msg", "Danh mục không tồn tại.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Nếu ảnh mới rỗng → giữ lại ảnh cũ
            if (danhmucsp.getImg() == null || danhmucsp.getImg().trim().isEmpty()) {
                danhmucsp.setImg(existing.getImg());
            }

            // Gán id lại (đảm bảo không null)
            danhmucsp.setId(id);
            danhmucspService.updateDanhmucsp(danhmucsp);

            response.put("status", "success");
            response.put("msg", "Cập nhật danh mục thành công!");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("msg", "Lỗi khi cập nhật: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteDanhmucsp(@PathVariable("id") int id) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            danhmucspService.deleteDanhmucsp(id);
            response.put("status", "success");
            response.put("msg", "Xoá danh mục thành công!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("msg", "Lỗi khi xoá: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addDanhmucsp(@ModelAttribute Danhmucsp danhmucsp) {
        danhmucspService.saveDanhmucsp(danhmucsp);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("msg", "Thêm Danh Mục Thành Công");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/category/upload-img")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> uploadCategoryImage(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/category/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = uploadDir + fileName;

            file.transferTo(new File(filePath));
            String link = "/uploads/category/" + fileName;

            response.put("status", "success");
            response.put("link", link);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            response.put("status", "error");
            response.put("msg", "Lỗi upload ảnh: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
