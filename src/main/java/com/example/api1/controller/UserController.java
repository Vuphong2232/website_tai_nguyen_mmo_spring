package com.example.api1.controller;

import com.example.api1.entity.User;

import com.example.api1.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import static com.example.api1.config.db.ranDom;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;

    @GetMapping("/list")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("userList", users);
        return "user/list";
    }
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }
    @GetMapping("/profile")
    public String ShowProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/users/login";
        model.addAttribute("user", user);
        return "auth/profile";
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("username", username);
            session.setAttribute("user", user);
            Map<String, Object> response = Map.of(
                    "status", "success",
                    "msg", "Đăng nhập thành công"
            );
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = Map.of(
                    "status", "error",
                    "msg", "Tài khoản hoặc mật khẩu không đúng"
            );
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addUser(@ModelAttribute User user) {
        user.setMoney("0");
        user.setTongNap("0");
        String token = ranDom();
        user.setToken(token);
        User createdUser = userService.addUser(user);
        Map<String, Object> response = Map.of(
                "status", "success",
                "msg", "Đăng Ký Thành Công"
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user/profile";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute User user) {
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/users/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users/list";
    }
    @PostMapping("/doi_mat_khau")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> doiMatKhau(@RequestParam("oldpass") String oldPassword,@RequestParam("newpass") String newPassword, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status", "error",
                    "msg", "Vui lòng đăng nhập"
            ));
        }
        if (!user.getPassword().equals(oldPassword)) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status", "error",
                    "msg", "Mật khẩu cũ không đúng!"
            ));
        }
        if (newPassword.length() < 4) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "msg", "Mật khẩu quá ngắn!"
            ));
        }
        user.setPassword(newPassword);
        userService.updateUser(user);
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "msg", "Đổi mật khẩu thành công!"
        ));
    }

}
