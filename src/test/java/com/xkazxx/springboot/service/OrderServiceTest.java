package com.xkazxx.springboot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {
	@Autowired
	OrderService orderService;

	@Test
	void makeOrder() throws InterruptedException {
		for (long i = 1; i <= 100; i++) {
			Thread.sleep(1000);
			orderService.makeOrder(i, i, i);
		}
	}
}