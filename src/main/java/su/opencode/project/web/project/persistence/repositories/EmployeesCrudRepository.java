package su.opencode.project.web.project.persistence.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import su.opencode.project.web.project.persistence.model.Employee;

import java.util.Date;
import java.util.Optional;

@Repository
public interface EmployeesCrudRepository extends CrudRepository<Employee, Long> {
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("select e from Employee e where e.birthDate < :date")
    Iterable<Employee> findAllByBirthDateLessThan(@Param("date") Date date);

    @Query("select e from Employee e where e.birthDate > :date")
    Iterable<Employee> findAllByBirthDateMoreThan(@Param("date") Date date);
}
