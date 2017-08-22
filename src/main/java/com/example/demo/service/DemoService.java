package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.LicenseRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.UUID.randomUUID;

/**
 * Created by HurleyD on 22/08/2017.
 */
@Service
@Transactional
public class DemoService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    LicenseRepository licenseRepository;

    public void createLicense() {

        Customer customer = null;
        Product product = null;
        try {
            customer = customerRepository.findByCustomerNumber("1");
            customerRepository.setSerializableLock(customer.getNodeId());
            customerRepository.removeLock(customer.getNodeId());

            product = productRepository.findByIsbn("1");
            productRepository.setSerializableLock(product.getNodeId());
            productRepository.removeLock(product.getNodeId());

            License license = licenseRepository.findLicense(customer.getCustomerNumber(), product.getIsbn());

            if (license == null) {
                license = new License(randomUUID().toString());

                CustomerLicenseRelationship customerLicenseRelationship = new CustomerLicenseRelationship(customer, license);
                customer.getContains().add(customerLicenseRelationship);

                LicenseProductRelationship licenseProductRelationship = new LicenseProductRelationship(license, product);
                license.setMapped(licenseProductRelationship);

                productRepository.save(product);
                customerRepository.save(customer);
                licenseRepository.save(license);
            }

        } finally {
            customerRepository.removeLock(customer.getNodeId());
            productRepository.removeLock(product.getNodeId());
        }
    }

    public void createLicenseOnlyRemove() {

        Customer customer = null;
        Product product = null;

        try {
            customer = customerRepository.findByCustomerNumber("1");
            customerRepository.removeLock(customer.getNodeId());

            product = productRepository.findByIsbn("1");
            productRepository.removeLock(product.getNodeId());

            License license = licenseRepository.findLicense(customer.getCustomerNumber(), product.getIsbn());

            if (license == null) {
                license = new License(randomUUID().toString());

                CustomerLicenseRelationship customerLicenseRelationship = new CustomerLicenseRelationship(customer, license);
                customer.getContains().add(customerLicenseRelationship);

                LicenseProductRelationship licenseProductRelationship = new LicenseProductRelationship(license, product);
                license.setMapped(licenseProductRelationship);

                productRepository.save(product);
                customerRepository.save(customer);
                licenseRepository.save(license);
            }

        } finally {
        }
    }

    public void createCustomer(String customerNumber) {
        Customer customer = new Customer(customerNumber);
        customerRepository.save(customer);
    }

    public void createProduct(String isbn) {
        Product product = new Product(isbn);
        productRepository.save(product);
    }
}
