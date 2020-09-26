package com.vaquar.microservice.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@RequiredArgsConstructor
@Component
public class CustomerApp {

    private final CustomerRepository customerRepository;

    @PostConstruct
    public void generateTestData() {
        customerRepository.save(new Customer("Eberhard", "Wolff",
                "eberhard.wolff@gmail.com", "Unter den Linden", "Berlin"));
        customerRepository.save(new Customer("Rod", "Johnson",
                "rod@somewhere.com", "Market Street", "San Francisco"));
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerApp.class, args);
    }

}
