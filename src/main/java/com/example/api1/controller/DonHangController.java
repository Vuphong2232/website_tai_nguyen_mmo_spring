package com.example.api1.controller;
import com.example.api1.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.api1.entity.DonHang;
import com.example.api1.entity.Sanpham;
import com.example.api1.repository.SanphamRepository;
import com.example.api1.service.DonHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static com.example.api1.config.db.rd;

@Controller
@RequestMapping("/donhang")
public class DonHangController {
    @Autowired
    private DonHangService donHangService;
    @Autowired
    private SanphamRepository sanphamRepository;

    @PostMapping("/buy_sp")
    public ResponseEntity<Map<String, Object>> themDonHang(@RequestBody DonHang donHang) {
        int idSp = donHang.getSanpham().getId();
        Sanpham sp = sanphamRepository.findById(idSp).orElse(null);
        if (sp == null) {
            Map<String, Object> error = Map.of(
                    "status", "error",
                    "msg", "Không tìm thấy sản phẩm với id_sp = " + idSp
            );
            return new ResponseEntity<>(error, HttpStatus.OK);
        }
        String ttSp = sp.getTtSp();
        if (ttSp == null || ttSp.isBlank()) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status", "error",
                    "msg", "Sản phẩm đã hết mã!"
            ));
        }
        String[] lines = ttSp.split("\\r?\\n");
        if (lines.length == 0 || lines[0].isBlank()) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status", "error",
                    "msg", "Dữ liệu tt_sp không hợp lệ"
            ));
        }
        String dongDauTien = lines[0];
        donHang.setNoiDungDonHang(dongDauTien);
        String newTtSp = Arrays.stream(lines)
                .skip(1)
                .collect(Collectors.joining("\n"));
        sp.setTtSp(newTtSp);
        sanphamRepository.save(sp);
        String token = rd();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = LocalDateTime.now().format(formatter);
        String money = sp.getPrice();
        donHang.setGia(money);
        donHang.setTime(date);
        donHang.setMaGiaoDich(token);
        donHang.setStatus("thanhcong");
        donHang.setSanpham(sp);
        donHangService.themDonHang(donHang);
        Map<String, Object> response = Map.of(
                "status", "success",
                "msg", "Mua hàng thành công"
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/lich-su-mua-hang")
    public String lichSuMua(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) return "redirect:/users/login";
        List<DonHang> donHangList = donHangService.getDonHangByUsername(username);
        int soLuongDon = 0;
        soLuongDon = donHangList.size();
        model.addAttribute("donHangList", donHangList);
        model.addAttribute("soLuongDon", soLuongDon);
        return "product/his_products";
    }
}
