package com.controller;

import com.Application;
import com.dao.CustomerOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class CustomerOrderControllerTest {

    private RestTemplate restTemplate = new TestRestTemplate("demo", "123");

    @Test
    public void findAll() {
        ResponseEntity<CustomerOrder> entity =
                restTemplate.getForEntity("http://localhost:9000/order", CustomerOrder.class);

        assertThat(entity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(entity.getBody().getCustomer()).isEqualTo("");
    }
}