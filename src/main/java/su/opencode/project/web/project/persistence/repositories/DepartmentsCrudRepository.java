package su.opencode.project.web.project.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import su.opencode.project.web.project.persistence.model.Department;

@Repository
public interface DepartmentsCrudRepository extends CrudRepository<Department, Long> {
}
