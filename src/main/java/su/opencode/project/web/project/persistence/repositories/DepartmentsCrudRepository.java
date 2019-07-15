package su.opencode.project.web.project.persistence.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import su.opencode.project.web.project.persistence.model.Department;

import java.util.Optional;

@Repository
public interface DepartmentsCrudRepository extends CrudRepository<Department, Long> {
    @Override
    @Query("select DISTINCT d from Department d join fetch d.employees")
    Iterable<Department> findAll();

    @Override
    @Query("select DISTINCT d from Department d join fetch d.employees where d.id = :id")
    Optional<Department> findById(@Param("id") Long id);
}
