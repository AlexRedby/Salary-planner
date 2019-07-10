package su.opencode.project.web.project.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.opencode.project.web.project.persistence.model.Position;
import su.opencode.project.web.project.persistence.repositories.PositionsCrudRepository;

@Service
public class PositionsDataService {
    @Autowired
    private PositionsCrudRepository positionsCrudRepository;

    @Transactional
    public Iterable<Position> findAll() {
        return positionsCrudRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        positionsCrudRepository.deleteById(id);
    }

    @Transactional
    public Position save(Position position) {
        return positionsCrudRepository.save(position);
    }
}
