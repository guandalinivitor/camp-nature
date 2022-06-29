package com.vng.connectcamp.controller;

import com.vng.connectcamp.model.RootEntryPointModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RootEntryPointController {

    @GetMapping
    public RootEntryPointModel root() {
        RootEntryPointModel model = new RootEntryPointModel();
        model.add(WebMvcLinkBuilder.linkTo(methodOn(CustomerController.class)
                .listAllCustomers()).withRel("customers"));
        return model;
    }
}
