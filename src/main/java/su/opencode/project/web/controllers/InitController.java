package su.opencode.project.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import su.opencode.project.web.project.persistence.model.Company;
import su.opencode.project.web.project.persistence.model.Department;
import su.opencode.project.web.project.persistence.model.Employee;
import su.opencode.project.web.project.persistence.model.Position;
import su.opencode.project.web.project.persistence.services.CompaniesDataService;
import su.opencode.project.web.project.persistence.services.DepartmentsDataService;
import su.opencode.project.web.project.persistence.services.EmployeesDataService;
import su.opencode.project.web.project.persistence.services.PositionsDataService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/init")
public class InitController {

    private EmployeesDataService employeesDataService;
    private CompaniesDataService companiesDataService;
    private PositionsDataService positionsDataService;
    private DepartmentsDataService departmentsDataService;

    @Autowired
    public InitController(
            EmployeesDataService employeesDataService,
            CompaniesDataService companiesDataService,
            PositionsDataService positionsDataService,
            DepartmentsDataService departmentsDataService
    ) {
        super();
        this.employeesDataService = employeesDataService;
        this.companiesDataService = companiesDataService;
        this.departmentsDataService = departmentsDataService;
        this.positionsDataService = positionsDataService;
    }

    // TODO: Add new init code for new entities
    @GetMapping
    public String init() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {
            // Компания
            Company c1 = new Company();
            c1.setName("Компания №1");
            c1 = companiesDataService.save(c1);

            // Отделы компании
            Department d1 = new Department();
            d1.setCompany(c1);
            d1.setName("Отдел разработки");
            d1 = departmentsDataService.save(d1);

            Department d2 = new Department();
            d2.setCompany(c1);
            d2.setName("Отдел кадров");
            d2 = departmentsDataService.save(d2);

            Department d3 = new Department();
            d3.setCompany(c1);
            d3.setName("Отдел маркетинга");
            d3 = departmentsDataService.save(d3);

            Department[] departments = new Department[] {d1, d2, d3};

            // Должности
            Position p1 = new Position();
            p1.setName("Java Developer");
            p1 = positionsDataService.save(p1);

            Position p2 = new Position();
            p2.setName("PHP Developer");
            p2 = positionsDataService.save(p2);

            Position p3 = new Position();
            p3.setName("HR");
            p3 = positionsDataService.save(p3);

            Position p4 = new Position();
            p4.setName("Marketer");
            p4 = positionsDataService.save(p4);

            Position[] positions = new Position[] {p1, p2, p3, p4};

            // Работники
            Employee emp = new Employee("Alex", new Date());
            emp.setFirstName("Alex");
            emp.setLastName("Vinogradow");
            emp.setBirthDate(sdf.parse("04-03-1997"));
            emp.setSalary(20_000);
            emp.setDepartment(d1);
            emp.setAverageTimeOfReceivingSalary(30);
            emp.setInWorkplace(true);
            emp.setPosition(p1);
            employeesDataService.save(emp);

            emp = new Employee("Alex", new Date());
            emp.setFirstName("Roma");
            emp.setLastName("Putin");
            emp.setBirthDate(sdf.parse("12-12-2000"));
            emp.setSalary(10_000);
            emp.setDepartment(d1);
            emp.setAverageTimeOfReceivingSalary(15);
            emp.setInWorkplace(true);
            emp.setPosition(p2);
            employeesDataService.save(emp);

            emp = new Employee("Alex", new Date());
            emp.setFirstName("Sasha");
            emp.setLastName("Bil");
            emp.setBirthDate(sdf.parse("20-01-2013"));
            emp.setSalary(15_000);
            emp.setDepartment(d1);
            emp.setAverageTimeOfReceivingSalary(60);
            emp.setInWorkplace(false);
            emp.setPosition(p3);
            employeesDataService.save(emp);

            Random rnd = new Random();
            for(int i = 0; i < 20; i++) {
                emp = new Employee("Alex", new Date());
                emp.setFirstName("Name" + i);
                emp.setLastName("Surname" + i);
                emp.setBirthDate(new Date());
                emp.setSalary(rnd.nextInt(50_000) + 10_000);
                emp.setDepartment(departments[rnd.nextInt(departments.length)]);
                emp.setAverageTimeOfReceivingSalary(rnd.nextInt(60) + 10);
                emp.setInWorkplace(rnd.nextBoolean());
                emp.setPosition(positions[rnd.nextInt(positions.length)]);
                employeesDataService.save(emp);
            }
        }
        catch (ParseException ex) {
            ex.printStackTrace();
        }

        return "redirect:/index";
    }
}
