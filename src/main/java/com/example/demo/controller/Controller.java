package com.example.demo.controller;

import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HurleyD on 22/08/2017.
 */
@RestController
public class Controller {

    @Autowired
    private DemoService licenseDemoService;

    @RequestMapping(value = {"/createLicense"}, method = RequestMethod.GET)
    public void createLicense() {
        licenseDemoService.createLicense();
    }

    @RequestMapping(value = {"/createLicenseOnlyRemove"}, method = RequestMethod.GET)
    public void createLicenseOnlyRemove() {
        licenseDemoService.createLicenseOnlyRemove();
    }

    @RequestMapping(value = {"/createCustomer/{customerNumber}"}, method = RequestMethod.GET)
    public void createCustomer(@PathVariable("customerNumber") String customerNumber) {
        licenseDemoService.createCustomer(customerNumber);
    }

    @RequestMapping(value = {"/createProduct/{isbn}"}, method = RequestMethod.GET)
    public void createProduct(@PathVariable("isbn") String isbn) {
        licenseDemoService.createProduct(isbn);
    }

}


