package com.vaquar.microservice.order.catalogstub;

import com.vaquar.microservice.order.clients.Item;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/catalog")
@Profile("test")
public class CatalogStub {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Item> getById(@PathVariable("id") long id) {
        if (id != 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Item(1, "iPod", 42.0),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public PagedResources<Item> getAll() {
        return new PagedResources<>(
                Collections.singletonList(new Item(1, "iPod", 42.0)),
                new PageMetadata(1, 0, 1));
    }

}
