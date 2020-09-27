package com.vaquar.microservice.order.logic;

import com.vaquar.microservice.order.clients.CatalogClient;
import com.vaquar.microservice.order.clients.Customer;
import com.vaquar.microservice.order.clients.CustomerClient;
import com.vaquar.microservice.order.clients.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final CustomerClient customerClient;
    private final CatalogClient catalogClient;

    // Constants
	private static final String SUCCESS = "success";
	private static final String ORDER = "order";

    @ModelAttribute("items")
    public Collection<Item> items() {
        return catalogClient.findAll();
    }

    @ModelAttribute("customers")
    public Collection<Customer> customers() {
        return customerClient.findAll();
    }

    @RequestMapping("/")
    public ModelAndView orderList() {
        return new ModelAndView("orderlist", "orders",
                orderRepository.findAll());
    }

    @GetMapping(value = "/form")
    public ModelAndView form() {
        return new ModelAndView("orderForm", ORDER, new Order());
    }

    @PostMapping(value = "/line")
    public ModelAndView addLine(Order order) {
        order.addLine(0, catalogClient.findAll().iterator().next().getItemId());
        return new ModelAndView("orderForm", ORDER, order);
    }

    @GetMapping(value = "/{id}")
    public ModelAndView get(@PathVariable("id") long id) {
        return new ModelAndView(ORDER, ORDER, orderRepository.findOne(id));
    }

    @PostMapping(value = "/")
    public ModelAndView post(Order order) {
        orderService.order(order);
        return new ModelAndView(SUCCESS);
    }

    @DeleteMapping(value = "/{id}")
    public ModelAndView post(@PathVariable("id") long id) {
        orderRepository.delete(id);
        return new ModelAndView(SUCCESS);
    }

}
