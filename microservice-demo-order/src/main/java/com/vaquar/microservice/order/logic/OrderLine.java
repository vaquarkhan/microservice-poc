package com.vaquar.microservice.order.logic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "F_COUNT")
    private int count;

    private long itemId;

    public OrderLine(int count, long item) {
        this.count = count;
        this.itemId = item;
    }
}
