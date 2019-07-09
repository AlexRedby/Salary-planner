package su.opencode.project.web.project.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.opencode.project.web.project.persistence.model.Company;
import su.opencode.project.web.project.persistence.repositories.CompaniesCrudRepository;

@Service
public class CompaniesDataService {

    @Autowired
    private CompaniesCrudRepository companiesCrudRepository;

    @Transactional
    public Iterable<Company> findAll() {
        return companiesCrudRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        companiesCrudRepository.deleteById(id);
    }

    @Transactional
    public Company save(Company employees) {
        return companiesCrudRepository.save(employees);
    }
}
