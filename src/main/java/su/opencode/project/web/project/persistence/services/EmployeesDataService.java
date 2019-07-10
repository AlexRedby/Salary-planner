package su.opencode.project.web.project.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.opencode.project.web.project.persistence.model.Employee;
import su.opencode.project.web.project.persistence.repositories.EmployeesCrudRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class EmployeesDataService {

    @Autowired
    private EmployeesCrudRepository employeesCrudRepository;

    @Transactional
    public Iterable<Employee> findAll() {
        return employeesCrudRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        employeesCrudRepository.deleteById(id);
    }

    @Transactional
    public Employee save(Employee employee) {
        return employeesCrudRepository.save(employee);
    }

    @Transactional
    public Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName) {
        return employeesCrudRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Transactional
    public Iterable<Employee> findAllByBirthDateMoreThan(Date date) {
        return employeesCrudRepository.findAllByBirthDateMoreThan(date);
    }

    @Transactional
    public Iterable<Employee> findAllByBirthDateLessThan(Date date) {
        return employeesCrudRepository.findAllByBirthDateLessThan(date);
    }
}
