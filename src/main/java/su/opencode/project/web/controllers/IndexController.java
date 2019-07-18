package su.opencode.project.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import su.opencode.project.web.project.persistence.model.Department;
import su.opencode.project.web.project.persistence.services.DepartmentsDataService;
import su.opencode.project.web.utils.MyMailSender;
import su.opencode.project.web.utils.PayrollQueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    MyMailSender mailSender;
    @Autowired
    PayrollQueue payrollQueue;
    @Autowired
    DepartmentsDataService departmentDataService;

    @GetMapping("/index")
    public String dummy() {
        return "redirect:/employees";
    }

    @GetMapping("/")
    public String empty() {
        return "redirect:/employees";
    }

    @GetMapping("/testMail")
    @ResponseBody
    public String makeTest() {
        mailSender.send("ak765akya852@mail.ru", "Greeting!", "Howdy bro!");

        return "Check your email box";
    }

    @GetMapping("/testQueue")
    @ResponseBody
    public PayrollQueue makeTestQueue() {
        List<Department> departments = new ArrayList<>(
                (Collection<? extends Department>) departmentDataService.findAll());
        payrollQueue.setEmployees(departments.get(0));
        payrollQueue.call();

        return payrollQueue;
    }
}