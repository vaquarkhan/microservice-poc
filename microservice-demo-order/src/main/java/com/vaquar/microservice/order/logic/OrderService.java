package com.vaquar.microservice.order.logic;

import com.vaquar.microservice.order.clients.CatalogClient;
import com.vaquar.microservice.order.clients.CustomerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final CatalogClient itemClient;

    public Order order(Order order) {
        if (order.getNumberOfLines() == 0) {
            throw new IllegalArgumentException("No order lines!");
        }
        if (!customerClient.isValidCustomerId(order.getCustomerId())) {
            throw new IllegalArgumentException("Customer does not exist!");
        }
        return orderRepository.save(order);
    }

    public double getPrice(long orderId) {
        return orderRepository.findOne(orderId).totalPrice(itemClient);
    }

}
