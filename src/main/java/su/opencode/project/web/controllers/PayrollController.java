package su.opencode.project.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import su.opencode.project.web.project.persistence.model.Department;
import su.opencode.project.web.project.persistence.model.Employee;
import su.opencode.project.web.project.persistence.services.DepartmentsDataService;
import su.opencode.project.web.project.persistence.services.EmployeesDataService;

import java.util.*;

@Controller
@RequestMapping("/payroll")
public class PayrollController {

    private EmployeesDataService employeesDataService;
    private DepartmentsDataService departmentsDataService;

    @Autowired
    public PayrollController(EmployeesDataService employeesDataService,
                             DepartmentsDataService departmentsDataService) {
        super();
        this.employeesDataService = employeesDataService;
        this.departmentsDataService = departmentsDataService;
    }

    // TODO: Rewrite
    @GetMapping
    public String getPayrollPage(Model m) {
        System.out.println("Starting processing getPayrollPage...");
        List<Employee> employees = new ArrayList<>(
                (Collection<? extends Employee>) employeesDataService.findAll());
        List<Department> departments = new ArrayList<>(
                (Collection<? extends Department>) departmentsDataService.findAll());
        Collection<Employee> calledEmployees = departments.get(0).getEmployees();

        m.addAttribute("departments", departments);
        m.addAttribute("employees", employees);
        m.addAttribute("calledEmployees", calledEmployees);

        return "payroll";
    }

    @PostMapping(value = "/employeesByDepartment", produces = "application/json")
    @ResponseBody
    public Collection<Employee> getEmployeesByDepartment(@RequestParam("department") Long id) {
        Optional<Department> department = departmentsDataService.findById(id);
        if(department.isPresent())
            return department.get().getEmployees();
        // TODO: Error!
        return new ArrayList<>();
    }
}
