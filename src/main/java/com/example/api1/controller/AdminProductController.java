package com.example.api1.controller;

import com.example.api1.entity.Sanpham;
import com.example.api1.entity.Danhmucsp;
import com.example.api1.service.SanphamService;
import com.example.api1.service.DanhmucspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminProductController {

    @Autowired
    private SanphamService sanphamService;

    @Autowired
    private DanhmucspService danhmucspService;

    @GetMapping("/product")
    public String showProductPage(Model model) {
        List<Sanpham> productList = sanphamService.getAllSanpham();
        model.addAttribute("productList", productList);
        return "admin/product";
    }

    // ✅ Danh sách sản phẩm dạng JSON
    @GetMapping("/product/list")
    public ResponseEntity<List<Sanpham>> getAllSanphamList() {
        List<Sanpham> productList = sanphamService.getAllSanpham();
        return ResponseEntity.ok(productList);
    }

    @PostMapping("/product/add")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addSanpham(@ModelAttribute Sanpham sanpham,
                                                          @RequestParam("categoryId") int categoryId) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            // Tìm danh mục theo ID
            Danhmucsp danhMuc = danhmucspService.getDanhmucspById(categoryId);
            if (danhMuc == null) {
                response.put("status", "error");
                response.put("msg", "Danh mục không tồn tại");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            // Gán danh mục vào sản phẩm
            sanpham.setDanhMucSp(danhMuc);

            // Lưu sản phẩm
            sanphamService.saveSanpham(sanpham);

            response.put("status", "success");
            response.put("msg", "Thêm sản phẩm thành công");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("msg", "Lỗi khi thêm: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



    // ✅ Cập nhật sản phẩm
    @PostMapping("/product/update/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateSanpham(@PathVariable("id") int id,
                                                             @ModelAttribute Sanpham sanpham,
                                                             @RequestParam("categoryId") int categoryId) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            // Kiểm tra sản phẩm có tồn tại không
            Sanpham existing = sanphamService.getSanphamById(id);
            if (existing == null) {
                response.put("status", "error");
                response.put("msg", "Sản phẩm không tồn tại");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Tìm danh mục theo ID
            Danhmucsp danhMuc = danhmucspService.getDanhmucspById(categoryId);
            if (danhMuc == null) {
                response.put("status", "error");
                response.put("msg", "Danh mục không tồn tại");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            // Gán lại ID và danh mục
            sanpham.setId(id);
            sanpham.setDanhMucSp(danhMuc);

            // Cập nhật sản phẩm
            sanphamService.updateSanpham(sanpham);

            response.put("status", "success");
            response.put("msg", "Cập nhật sản phẩm thành công");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("msg", "Lỗi khi cập nhật: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @DeleteMapping("/product/delete/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteSanpham(@PathVariable("id") int id) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            // Kiểm tra sản phẩm có tồn tại không
            Sanpham existing = sanphamService.getSanphamById(id);
            if (existing == null) {
                response.put("status", "error");
                response.put("msg", "Sản phẩm không tồn tại hoặc đã bị xoá.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Thực hiện xóa
            sanphamService.deleteSanpham(id);

            response.put("status", "success");
            response.put("msg", "Xóa sản phẩm thành công");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi chi tiết
            response.put("status", "error");
            response.put("msg", "Lỗi khi xoá: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
