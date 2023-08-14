package com.thepinkproject.the_pink_project.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "text", defaultValue = "Hello World") String text, ModelMap model) {
        model.addAttribute("text", text);

        return "thisHelloFile";
    }

}