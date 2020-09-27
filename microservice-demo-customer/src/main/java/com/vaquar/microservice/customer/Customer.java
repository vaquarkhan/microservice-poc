package com.vaquar.microservice.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@EqualsAndHashCode
@ToString
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    public Customer() {
        super();
        id = 0l;
    }

    public Customer(String firstname, String name, String email, String street,
                    String city) {
        super();
        this.name = name;
        this.firstname = firstname;
        this.email = email;
        this.street = street;
        this.city = city;
    }
}
