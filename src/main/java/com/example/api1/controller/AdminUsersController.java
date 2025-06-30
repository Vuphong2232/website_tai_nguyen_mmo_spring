package com.example.api1.controller;

import com.example.api1.entity.User;
import com.example.api1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminUsersController {

    @Autowired
    private UserService userService;

    // Hiển thị giao diện users + danh sách
    @GetMapping("/users")
    public String showPageAdmin(Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("userList", list); // <- BẮT BUỘC PHẢI CÓ
        return "admin/users"; // hoặc đường dẫn HTML bạn dùng
    }


    // ✅ Danh sách user dạng JSON
    @GetMapping("/users/list")
    public ResponseEntity<List<User>> getAllUserList() {
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/users/update/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> updateUser(
            @PathVariable int id,
            @ModelAttribute User user) {
        try {
            user.setId(id); // Gán lại ID lấy từ URL
            userService.updateUser(user);

            Map<String, String> result = new HashMap<>();
            result.put("status", "success");
            result.put("msg", "Cập nhật người dùng thành công!");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("status", "error");
            error.put("msg", "Cập nhật thất bại: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }


    // Xóa user (gọi từ form/xác nhận trong users.html)
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/lock/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> lockUser(@PathVariable int id) {
        Map<String, String> result = new HashMap<>();
        try {
            User user = userService.getUserById(id);
            if (user == null) {
                result.put("status", "error");
                result.put("msg", "Không tìm thấy user.");
                return ResponseEntity.ok(result);
            }
            user.setLevel(3); // Gán level = 3 để khóa
            userService.updateUser(user);

            result.put("status", "success");
            result.put("msg", "Người dùng đã bị khóa.");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("status", "error");
            result.put("msg", "Lỗi: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

}
