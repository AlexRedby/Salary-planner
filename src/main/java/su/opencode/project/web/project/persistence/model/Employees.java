package su.opencode.project.web.project.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by popov on 12.10.18.
 */
@Entity
@Table(name = "EMPLOYEES")
public class Employees implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "BIRTH_DATE", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date birthDate;
    @ManyToOne
    @JoinColumn(name = "COMPANY_ID", nullable = false)
    private Company company;
    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;
    @Column(name = "CREATED_WHEN", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdWhen;

    public Employees() {
    }

    public Employees(String createdBy, Date createdWhen) {
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
        Employees employees = (Employees) o;
        return Objects.equals(getId(), employees.getId()) &&
                Objects.equals(getFirstName(), employees.getFirstName()) &&
                Objects.equals(getLastName(), employees.getLastName()) &&
                Objects.equals(getBirthDate(), employees.getBirthDate()) &&
                Objects.equals(getCompany(), employees.getCompany()) &&
                Objects.equals(getCreatedBy(), employees.getCreatedBy()) &&
                Objects.equals(getCreatedWhen(), employees.getCreatedWhen());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getBirthDate(), getCompany(), getCreatedBy(), getCreatedWhen());
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", company=" + company.toString() +
                ", createdBy='" + createdBy + '\'' +
                ", createdWhen=" + createdWhen +
                '}';
    }
}
