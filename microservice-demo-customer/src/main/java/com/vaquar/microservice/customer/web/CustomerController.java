package com.vaquar.microservice.customer.web;

import com.vaquar.microservice.customer.Customer;
import com.vaquar.microservice.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomerController {

    public static final String SUCCESS = "success";
    private CustomerRepository customerRepository;

    public static final String CUSTOMER = "customer";

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/{id}.html", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView customerById(@PathVariable("id") long id) {
        return new ModelAndView(CUSTOMER, CUSTOMER,
                customerRepository.findOne(id));
    }

    @GetMapping("/list.html")
    public ModelAndView customerList() {
        return new ModelAndView("customerlist", "customers",
                customerRepository.findAll());
    }

    @GetMapping(value = "/form.html")
    public ModelAndView add() {
        return new ModelAndView(CUSTOMER, CUSTOMER, new Customer());
    }

    @PostMapping(value = "/form.html")
    public ModelAndView post(Customer customer, HttpServletRequest httpRequest) {
        customerRepository.save(customer);
        return new ModelAndView(SUCCESS);
    }

    @PutMapping(value = "/{id}.html")
    public ModelAndView put(@PathVariable("id") long id, Customer customer,
                            HttpServletRequest httpRequest) {
        customer.setId(id);
        customerRepository.save(customer);
        return new ModelAndView(SUCCESS);
    }

    @DeleteMapping(value = "/{id}.html")
    public ModelAndView delete(@PathVariable("id") long id) {
        customerRepository.delete(id);
        return new ModelAndView(SUCCESS);
    }

}
