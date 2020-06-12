package com.inovision.commander.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    // src/main/resources/templates/home.html
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("name", "Boot Commander");
        return "home";
    }
}
