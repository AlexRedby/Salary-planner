package su.opencode.project.web.project.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import su.opencode.project.web.project.persistence.model.Position;

@Repository
public interface PositionsCrudRepository extends CrudRepository<Position, Long> {
}
