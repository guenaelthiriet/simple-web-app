package com.config;

import com.controller.CustomerController;
import com.controller.OrderController;
import com.controller.ProductController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		registerEndpoints();
	}

	private void registerEndpoints() {
		register(CustomerController.class);
		register(OrderController.class);
		register(ProductController.class);
	}
}