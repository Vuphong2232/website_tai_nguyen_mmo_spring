package com.example.api1.config;

import com.example.api1.entity.Danhmucsp;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class db {

    public static String ranDom() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder transactionCode = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            int randomIndex = random.nextInt(characters.length());
            transactionCode.append(characters.charAt(randomIndex));
        }

        return transactionCode.toString();
    }
    public static String rd() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder transactionCode = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(characters.length());
            transactionCode.append(characters.charAt(randomIndex));
        }

        return transactionCode.toString();
    }
}
