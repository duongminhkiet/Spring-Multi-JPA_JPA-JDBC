package com.zmk.spring.test.rd.jpa.multi2.customer.data2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true,nullable = false)
    private String email;
    private String firstName;
    private String lastName;

    protected EmployeeModel() {}

    public EmployeeModel(String email,String firstName, String lastName) {
        this.email=email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s',email='%s']",
                id, firstName, lastName,email);
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}