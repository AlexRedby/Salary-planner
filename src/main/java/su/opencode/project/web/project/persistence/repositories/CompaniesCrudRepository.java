package su.opencode.project.web.project.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import su.opencode.project.web.project.persistence.model.Company;

@Repository
public interface CompaniesCrudRepository extends CrudRepository<Company, Long> {
}
