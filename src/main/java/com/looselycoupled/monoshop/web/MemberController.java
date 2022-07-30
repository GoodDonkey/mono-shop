package com.looselycoupled.monoshop.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;

@Slf4j
@Controller
public class MemberController {
    
    @PostMapping("/api/v1/signup")
    public String signup(@Valid @ModelAttribute("member") MemberSignupRequest memberDTO,
                         BindingResult bindingResult) {
        log.debug("MemberSignupRequest: {}", memberDTO);
        if (bindingResult.hasErrors()) {
            HashMap<String, String> errors = new HashMap<>();
            bindingResult
                     .getAllErrors()
                     .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
            log.debug("errors: {}", errors);
            return "redirect:/signup";
        }
        return "redirect:/";
    }
}
