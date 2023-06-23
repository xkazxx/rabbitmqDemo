package com.xkazxx.springboot.service;

import com.xkazxx.springboot.common.Constant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2022/12/14 21:33
 **/
@Component
public class OrderService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void makeOrder(Long userId, Long productId, long num) {
		// 1： 模拟用户下单
		String orderNumber = UUID.randomUUID().toString();
		System.out.println("用户 " + userId + ",订单编号是：" + orderNumber);
		// 发送订单信息给RabbitMQ fanout
		rabbitTemplate.convertAndSend(Constant.FANOUT_EXCHANGE_NAME, "", orderNumber);
	}
}