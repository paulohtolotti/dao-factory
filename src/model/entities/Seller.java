package model.entities;

import exceptions.DomainException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Seller implements Serializable {

    private Integer id;
    private Department department;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Double baseSalary;

    public Seller (int id, Department department, String name, String email, LocalDate brithDate, double baseSalary) {

        if (baseSalary <= 0) throw new DomainException("Invalid value for baseSalary");

        this.id = id;
        this.department = department;
        this.name = name;
        this.email = email;
        this.birthDate = brithDate;
        this.baseSalary = baseSalary;

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Seller #");
        sb.append(this.id);
        sb.append(": ");
        sb.append(this.name);
        sb.append(". Department: ");
        sb.append(this.department.getName());
        sb.append(". E-mail: ");
        sb.append(this.email);
        sb.append(". Birthday: ");
        sb.append(this.birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        sb.append(". Base salary $");
        sb.append(this.baseSalary);

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || this.getClass() != o.getClass()) return false;

        Seller s = (Seller) o;
        return this.getId().equals(s.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
