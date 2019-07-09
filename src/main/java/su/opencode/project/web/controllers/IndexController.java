package su.opencode.project.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String dummy() {
        return "redirect:/employees";
    }

    @GetMapping("/")
    public String empty() {
        return "redirect:/employees";
    }
}