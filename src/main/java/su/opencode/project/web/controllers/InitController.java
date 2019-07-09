package su.opencode.project.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import su.opencode.project.web.project.persistence.model.Company;
import su.opencode.project.web.project.persistence.model.Employees;
import su.opencode.project.web.project.persistence.services.CompaniesDataService;
import su.opencode.project.web.project.persistence.services.EmployeesDataService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/init")
public class InitController {

    @Autowired
    EmployeesDataService employeesDataService;
    @Autowired
    CompaniesDataService companiesDataService;

    @GetMapping
    public String init() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Company c1 = new Company();
            c1.setName("Компания №1");
            c1 = companiesDataService.save(c1);

            Company c2 = new Company();
            c2.setName("Компания №2");
            c2 = companiesDataService.save(c2);

            Employees emp = new Employees("Alex", new Date());
            emp.setFirstName("Alex");
            emp.setLastName("Vinogradow");
            emp.setBirthDate(sdf.parse("04-03-1997"));
            emp.setCompany(c1);
            employeesDataService.save(emp);

            emp = new Employees("Alex", new Date());
            emp.setFirstName("Roma");
            emp.setLastName("Putin");
            emp.setBirthDate(sdf.parse("12-12-2000"));
            emp.setCompany(c1);
            employeesDataService.save(emp);

            emp = new Employees("Alex", new Date());
            emp.setFirstName("Sasha");
            emp.setLastName("Bil");
            emp.setBirthDate(sdf.parse("20-01-2013"));
            emp.setCompany(c2);
            employeesDataService.save(emp);
        }
        catch (ParseException ex) {
            ex.printStackTrace();
        }

        return "redirect:/index";
    }
}
