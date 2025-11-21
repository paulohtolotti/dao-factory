package model;

import exceptions.DomainException;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {

    private Integer id;
    private String name;

    public Department(Integer id, String name) {

        if (id < 0) throw new DomainException("Id can't be a negative integer");
        if(name.length() < 2) throw new DomainException("Department name too short");

        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        sb.append(this.getId());
        sb.append(": ");
        sb.append(this.getName());

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || this.getClass() != o.getClass()) return false;

        Department d = (Department) o;

        return this.getId().equals(d.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
