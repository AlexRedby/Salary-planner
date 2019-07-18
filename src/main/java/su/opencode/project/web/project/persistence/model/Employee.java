package su.opencode.project.web.project.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "EMPLOYEES")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "BIRTH_DATE", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date birthDate;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "SALARY", nullable = false)
    private int salary;

    @Column(name = "LAST_SALARY_DATE")
    private LocalDate lastSalaryDate;

    @Column(name = "AVG_TIME_RECEIVING_SALARY")
    private int averageTimeOfReceivingSalary;

    @ManyToOne
    @JoinColumn(name = "POSITION_ID", nullable = false)
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID", nullable = false)
    private Department department;

    @Column(name = "IN_WORKPLACE", nullable = false)
    private boolean inWorkplace;

    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "CREATED_WHEN", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdWhen;

    public Employee() {
    }

    public Employee(String createdBy, Date createdWhen) {
        this.createdBy = createdBy;
        this.createdWhen = createdWhen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDate getLastSalaryDate() {
        return lastSalaryDate;
    }

    public void setLastSalaryDate(LocalDate lastSalaryDate) {
        this.lastSalaryDate = lastSalaryDate;
    }

    public int getAverageTimeOfReceivingSalary() {
        return averageTimeOfReceivingSalary;
    }

    public void setAverageTimeOfReceivingSalary(int averageTimeOfReceivingSalary) {
        this.averageTimeOfReceivingSalary = averageTimeOfReceivingSalary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean isInWorkplace() {
        return inWorkplace;
    }

    public void setInWorkplace(boolean inWorkplace) {
        this.inWorkplace = inWorkplace;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedWhen() {
        return createdWhen;
    }

    public void setCreatedWhen(Date createdWhen) {
        this.createdWhen = createdWhen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(birthDate, employee.birthDate) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(createdBy, employee.createdBy) &&
                Objects.equals(createdWhen, employee.createdWhen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, email, createdBy, createdWhen);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", birthDate='" + email + '\'' +
                ", salary=" + salary +
                ", lastSalaryDate=" + lastSalaryDate +
                ", averageTimeOfReceivingSalary=" + averageTimeOfReceivingSalary +
                ", position=" + position +
                ", department=" + (department != null ? department.getName() : "") +
                ", inWorkplace=" + inWorkplace +
                ", createdBy='" + createdBy + '\'' +
                ", createdWhen=" + createdWhen +
                '}';
    }
}
