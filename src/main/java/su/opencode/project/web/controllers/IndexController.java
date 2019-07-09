package su.opencode.project.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @GetMapping("/pages/index")
    public String index() {
        return "redirect:/employees";
    }

    @GetMapping("/index")
    public String dummy() {
        return "redirect:/employees";
    }

    @GetMapping("/")
    public String empty() {
        return "redirect:/employees";
    }
}