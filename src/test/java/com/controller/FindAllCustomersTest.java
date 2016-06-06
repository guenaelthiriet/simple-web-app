package com.controller;

import com.Application;
import com.controller.support.Page;
import com.controller.support.PageAssertion;
import com.dao.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class FindAllCustomersTest {

    private RestTemplate restTemplate = new TestRestTemplate("demo", "123");

    @Test
    public void returnsAllPages() {
        // act
        ResponseEntity<Page<Customer>> responseEntity = getCustomers(
                "http://localhost:9000/customer"
        );
        Page<Customer> customerPage = responseEntity.getBody();
        // assert
        PageAssertion.assertThat(customerPage)
                .hasTotalElements(3)
                .hasTotalPages(1)
                .hasPageSize(20)
                .hasPageNumber(0)
                .hasContentSize(3);
    }

    @Test
    public void returnsCustomPage() {

        // act
        ResponseEntity<Page<Customer>> responseEntity = getCustomers(
                "http://localhost:9000/customer?page=0&size=1&sort=firstname&direction=desc"
        );
        // assert
        Page<Customer> customerPage = responseEntity.getBody();

        PageAssertion.assertThat(customerPage)
                .hasTotalElements(3)
                .hasTotalPages(3)
                .hasPageSize(1)
                .hasPageNumber(0)
                .hasContentSize(1);
    }

    private ResponseEntity<Page<Customer>> getCustomers(String uri) {
        return restTemplate.exchange(
                uri, HttpMethod.GET, null, getParameterizedPageTypeReference()
        );
    }

    private ParameterizedTypeReference<Page<Customer>> getParameterizedPageTypeReference() {
        return new ParameterizedTypeReference<Page<Customer>>() {
        };
    }
}