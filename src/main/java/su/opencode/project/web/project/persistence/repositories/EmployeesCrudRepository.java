package su.opencode.project.web.project.persistence.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import su.opencode.project.web.project.persistence.model.Employees;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeesCrudRepository extends CrudRepository<Employees, Long> {
    Optional<Employees> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("select e from Employees e where e.birthDate < :date")
    Iterable<Employees> findAllByBirthDateLessThan(@Param("date") Date date);

    @Query("select e from Employees e where e.birthDate > :date")
    Iterable<Employees> findAllByBirthDateMoreThan(@Param("date") Date date);
}
