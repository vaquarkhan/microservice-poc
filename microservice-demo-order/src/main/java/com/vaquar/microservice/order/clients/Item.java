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
public class Item extends ResourceSupport {

    private String name;

    private double price;

    @JsonProperty("id")
    private long itemId;

    public Item(long id, String name, double price) {
        super();
        this.itemId = id;
        this.name = name;
        this.price = price;
    }

}
