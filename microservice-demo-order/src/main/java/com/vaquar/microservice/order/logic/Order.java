package com.vaquar.microservice.order.logic;

import com.vaquar.microservice.order.clients.CatalogClient;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "ORDERTABLE")
class Order {

    @Id
    @GeneratedValue
    private long id;

    private long customerId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLine> orderLine;

    public Order() {
        super();
        orderLine = new ArrayList<>();
    }

    public Order(long customerId) {
        super();
        this.customerId = customerId;
        this.orderLine = new ArrayList<>();
    }

    public void addLine(int count, long itemId) {
        this.orderLine.add(new OrderLine(count, itemId));
    }

    public int getNumberOfLines() {
        return orderLine.size();
    }

    public double totalPrice(CatalogClient itemClient) {
        return orderLine.stream()
                .map((ol) -> ol.getCount() * itemClient.price(ol.getItemId()))
                .reduce(0.0, (d1, d2) -> d1 + d2);
    }

    public void setCustomer(long customerId) {
        this.customerId = customerId;
    }
}
