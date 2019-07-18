package su.opencode.project.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import su.opencode.project.web.project.persistence.model.Department;
import su.opencode.project.web.project.persistence.model.Employee;
import su.opencode.project.web.project.persistence.services.DepartmentsDataService;
import su.opencode.project.web.project.persistence.services.EmployeesDataService;
import su.opencode.project.web.utils.PayrollQueue;

import java.util.*;

@Controller
@RequestMapping("/payroll")
public class PayrollController {

    private EmployeesDataService employeesDataService;
    private DepartmentsDataService departmentsDataService;
    private PayrollQueue payrollQueue;

    @Autowired
    public PayrollController(EmployeesDataService employeesDataService,
                             DepartmentsDataService departmentsDataService,
                             PayrollQueue payrollQueue) {
        super();
        this.employeesDataService = employeesDataService;
        this.departmentsDataService = departmentsDataService;
        this.payrollQueue = payrollQueue;
    }

    @GetMapping
    public String getPayrollPage(Model m) {
        List<Department> departments = new ArrayList<>(
                (Collection<? extends Department>) departmentsDataService.findAll());

        m.addAttribute("departments", departments);

        return "payroll";
    }

    @PostMapping(value = "/issueSalary", produces = "application/json")
    @ResponseBody
    public Boolean issueSalary(@RequestParam("id") Long id) {
        payrollQueue.release(id, true);
        return true;
    }

    @PostMapping(value = "/callNewEmployees", produces = "application/json")
    @ResponseBody
    public Collection<Employee> callNewEmployees(
            @RequestParam(value = "truants[]", required = false) Long[] truantsIds) {
        // Отправляем не пришедших в конец очереди, если есть таковые
        if(truantsIds != null)
            for (Long id : truantsIds)
                payrollQueue.release(id, false);

        // Вызываем новых
        payrollQueue.call();

        return payrollQueue.getCalledEmployees();
    }

    @PostMapping(value = "/employeesByDepartment", produces = "application/json")
    @ResponseBody
    public PayrollQueue getEmployeesByDepartment(@RequestParam("department") Long id) {
        Optional<Department> department = departmentsDataService.findById(id);
        if(department.isPresent()) {
            payrollQueue.setEmployees(department.get());
            return payrollQueue;
        }
        // TODO: Error!
        return null;
    }
}
