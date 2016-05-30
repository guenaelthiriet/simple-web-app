package com.controller;

import com.Application;
import com.dao.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class SaveCustomerTest {

    private RestTemplate restTemplate = new TestRestTemplate("demo", "123");

@Test
public void savesCustomer() {
    // act
    URI uri = restTemplate.postForLocation("http://localhost:9000/customer",
            new Customer("email", "John", "Doe", "address"));
    // assert
    ResponseEntity<Customer> responseEntity =
            restTemplate.getForEntity(uri, Customer.class);

    Customer customer = responseEntity.getBody();

    assertThat(customer.getFirstName())
            .isEqualTo("John");
    assertThat(customer.getLastName())
            .isEqualTo("Doe");
}
}