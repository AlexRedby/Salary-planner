package su.opencode.project.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import su.opencode.project.web.project.persistence.model.Company;
import su.opencode.project.web.project.persistence.model.Employees;
import su.opencode.project.web.project.persistence.services.CompaniesDataService;
import su.opencode.project.web.project.persistence.services.EmployeesDataService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    EmployeesDataService employeesDataService;
    @Autowired
    CompaniesDataService companiesDataService;

    @GetMapping
    public ModelAndView getEmployeesPage() {
        List<Employees> employees = new ArrayList<>();
        employees.addAll((Collection<? extends Employees>) employeesDataService.findAll());

        return getEmployeesModalAndView(employees);
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        employeesDataService.deleteById(id);

        return "redirect:/employees";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("birthDate") String birthDate,
            @RequestParam("id") Long id,
            @RequestParam("company") Long companyId
    ) {
        try {
            Company company = new Company();
            company.setId(companyId);

            Employees newEmployee = new Employees("Alex", new Date());
            newEmployee.setId(id);
            newEmployee.setFirstName(firstName);
            newEmployee.setLastName(lastName);
            newEmployee.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(birthDate));
            newEmployee.setCompany(company);

            // Если есть id обновит запись, иначе добавит как новую
            employeesDataService.save(newEmployee);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "redirect:/employees";
    }

    @PostMapping("/findByName")
    public ModelAndView findByName(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
    ) {
        List<Employees> employees = new ArrayList<>();

        Optional<Employees> optionalEmployees = employeesDataService.findByFirstNameAndLastName(firstName, lastName);
        optionalEmployees.ifPresent(employees::add);

        return getEmployeesModalAndView(employees);

    }

    @PostMapping("/findByAge")
    public ModelAndView findByAge(
            @RequestParam("compare") String compare,
            @RequestParam("age") Integer age
    ) {
        List<Employees> employees = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -age);
        Date date = c.getTime();

        switch (compare) {
            case "younger":
                employees.addAll((Collection<? extends Employees>) employeesDataService.findAllByBirthDateMoreThan(date));
                break;
            case "older":
                employees.addAll((Collection<? extends Employees>) employeesDataService.findAllByBirthDateLessThan(date));
                break;
        }

        return getEmployeesModalAndView(employees);
    }

    private ModelAndView getEmployeesModalAndView(List<Employees> employees) {
        List<Company> companies = new ArrayList<>();
        companies.addAll((Collection<? extends Company>) companiesDataService.findAll());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employees", employees);
        modelAndView.addObject("companies", companies);
        modelAndView.setViewName("employees");
        return modelAndView;
    }
}
