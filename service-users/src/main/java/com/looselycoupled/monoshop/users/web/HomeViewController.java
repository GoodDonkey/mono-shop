package com.looselycoupled.monoshop.users.web;

import com.looselycoupled.monoshop.users.SignupRequest;
import com.looselycoupled.monoshop.common.web.dtos.LoginForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Slf4j
public class HomeViewController {
    
    @GetMapping
    public String home(){
        return "home";
    }
    
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form){
        return "loginForm";
    }
    
    @GetMapping("/signup")
    public String signupForm(@ModelAttribute("member") SignupRequest member) {
        return "signupForm";
    }
}
