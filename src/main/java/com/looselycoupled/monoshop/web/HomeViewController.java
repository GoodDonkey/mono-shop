package com.looselycoupled.monoshop.web;

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
    public String signupForm(@ModelAttribute("member") MemberSignupRequest member) {
        return "signupForm";
    }
}
