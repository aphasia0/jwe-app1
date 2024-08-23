package com.example.app1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/")
    @CrossOrigin(origins = "*")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to our homepage APP1!");
        return "home";
    }
}
