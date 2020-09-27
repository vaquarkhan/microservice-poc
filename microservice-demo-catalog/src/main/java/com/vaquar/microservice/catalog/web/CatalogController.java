package com.vaquar.microservice.catalog.web;

import com.vaquar.microservice.catalog.Item;
import com.vaquar.microservice.catalog.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CatalogController {

    private final ItemRepository itemRepository;
    private static final String SUCCESS = "success";

    @Autowired
    public CatalogController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping(value = "/{id}.html", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView Item(@PathVariable("id") long id) {
        return new ModelAndView("item", "item", itemRepository.findOne(id));
    }

    @GetMapping("/list.html")
    public ModelAndView ItemList() {
        return new ModelAndView("itemlist", "items", itemRepository.findAll());
    }

    @GetMapping(value = "/form.html")
    public ModelAndView add() {
        return new ModelAndView("item", "item", new Item());
    }

    @PostMapping(value = "/form.html")
    public ModelAndView post(Item item) {
        itemRepository.save(item);
        return new ModelAndView(SUCCESS);
    }

    @PutMapping(value = "/{id}.html")
    public ModelAndView put(@PathVariable("id") long id, Item item) {
        item.setId(id);
        itemRepository.save(item);
        return new ModelAndView(SUCCESS);
    }

    @GetMapping(value = "/searchForm.html", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView searchForm() {
        return new ModelAndView("searchForm");
    }

    @GetMapping(value = "/searchByName.html", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView search(@RequestParam("query") String query) {
        return new ModelAndView("itemlist", "items",
                itemRepository.findByNameContaining(query));
    }

    @DeleteMapping(value = "/{id}.html")
    public ModelAndView delete(@PathVariable("id") long id) {
        itemRepository.delete(id);
        return new ModelAndView(SUCCESS);
    }

}
