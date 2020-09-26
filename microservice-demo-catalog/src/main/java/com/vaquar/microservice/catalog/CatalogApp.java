package com.vaquar.microservice.catalog;

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
public class CatalogApp {

    private final ItemRepository itemRepository;

    @PostConstruct
    public void generateTestData() {
        itemRepository.save(new Item("iPod", 42.0));
        itemRepository.save(new Item("iPod touch", 21.0));
        itemRepository.save(new Item("iPod nano", 1.0));
        itemRepository.save(new Item("Apple TV", 100.0));
    }

    public static void main(String[] args) {
        SpringApplication.run(CatalogApp.class, args);
    }

}
