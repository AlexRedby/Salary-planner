package su.opencode.project.web.project.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.opencode.project.web.project.persistence.model.Department;
import su.opencode.project.web.project.persistence.repositories.DepartmentsCrudRepository;

import java.util.Optional;

@Service
public class DepartmentsDataService {
    @Autowired
    private DepartmentsCrudRepository departmentsCrudRepository;

    @Transactional
    public Iterable<Department> findAll() {
        return departmentsCrudRepository.findAll();
    }

    @Transactional
    public Optional<Department> findById(Long id) {
        return departmentsCrudRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        departmentsCrudRepository.deleteById(id);
    }

    @Transactional
    public Department save(Department department) {
        return departmentsCrudRepository.save(department);
    }
}
