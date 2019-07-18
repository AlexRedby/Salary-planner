package su.opencode.project.web.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.opencode.project.web.project.persistence.model.Department;
import su.opencode.project.web.project.persistence.model.Employee;
import su.opencode.project.web.project.persistence.services.EmployeesDataService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@JsonIgnoreProperties(value = {"mailSender", "employeesDataService", "maxCountCalledEmployees"})
public class PayrollQueue {
    @JsonProperty("mailSender")
    private MyMailSender mailSender;
    @JsonProperty("employeesDataService")
    private EmployeesDataService employeesDataService;

    private Set<Employee> calledEmployees;
    private Queue<Employee> waitingEmployees;
    private Set<Employee> issuedEmployees;

    @JsonProperty("maxCountCalledEmployees")
    private int maxCountCalledEmployees;

    @Autowired
    public PayrollQueue(MyMailSender mailSender, EmployeesDataService employeesDataService) {
        this.employeesDataService = employeesDataService;
        this.mailSender = mailSender;
        calledEmployees = new HashSet<>();
        waitingEmployees = new LinkedList<>();
        issuedEmployees = new HashSet<>();
        maxCountCalledEmployees = 5;
    }

    public int getMaxCountCalledEmployees() {
        return maxCountCalledEmployees;
    }

    public void setMaxCountCalledEmployees(int maxCountCalledEmployees) {
        this.maxCountCalledEmployees = maxCountCalledEmployees;
    }

    public Set<Employee> getCalledEmployees() {
        return new HashSet<>(calledEmployees);
    }

    public Queue<Employee> getWaitingEmployees() {
        return new LinkedList<>(waitingEmployees);
    }

    public Set<Employee> getIssuedEmployees() {
        return new HashSet<>(issuedEmployees);
    }

    public boolean isEmpty() {
        return calledEmployees.isEmpty();
    }

    // Изменяет дату последнего получения зарплаты,
    // сохраняет в БД и
    // перемещает работника из calledEmployees в issuedEmployees
    private void release(Employee emp, boolean issued) {
        if(emp != null) {
            if(issued) {
                emp.setLastSalaryDate(LocalDate.now());
                employeesDataService.save(emp);
                issuedEmployees.add(emp);
            } else
                waitingEmployees.add(emp);
            calledEmployees.remove(emp);
        }
    }

    // Находит работника по id и передаёт его перегруженному методу
    public void release(Long id, boolean issued) {
        release(calledEmployees.stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElse(null) , issued);
    }

    public void call() {
        if(calledEmployees.isEmpty()) {
            waitingEmployees.stream().limit(maxCountCalledEmployees).forEach(e -> {
                mailSender.send(
                        e.getEmail(),
                        "Salary! Hooray!!!",
                        "Come and take your salary right now!");
                calledEmployees.add(e);
            });
            waitingEmployees.removeAll(
                    waitingEmployees.stream()
                            .limit(maxCountCalledEmployees)
                            .collect(Collectors.toList()));
        }
    }

    public void setEmployees(Department department) {
        waitingEmployees.clear();
        issuedEmployees.clear();


        department.getEmployees().forEach(e -> {
            // Если дата последней выдачи + ~полмесяца(2 недели) > текущей даты,
            // то переместить работника в issuedEmployees
            if(e.getLastSalaryDate() != null && e.getLastSalaryDate().plusWeeks(2).isAfter(LocalDate.now()))
                issuedEmployees.add(e);
            else if(!calledEmployees.contains(e))
                waitingEmployees.add(e);
        });
    }
}
