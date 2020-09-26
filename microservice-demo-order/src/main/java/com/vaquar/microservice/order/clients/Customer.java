package com.vaquar.microservice.order.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Customer extends ResourceSupport {

    private String name;

    private String firstname;

    private String email;

    private String street;

    private String city;

    @JsonProperty("id")
    private long customerId;

    public Customer(long id, String firstname, String name, String email,
                    String street, String city) {
        super();
        this.customerId = id;
        this.firstname = firstname;
        this.name = name;
        this.email = email;
        this.street = street;
        this.city = city;
    }

}
