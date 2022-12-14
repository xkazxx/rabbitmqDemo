package com.xkazxx.common;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public interface Constant {
	String HOST = "192.168.2.29";
	int PORT = 5672;
	String PASSWORD = "admin";
	String USER_NAME = "admin";
	String VIRTUAL_HOST = "/demo";
	String UNKNOWN_EXCHANGE_NAME = "unknown_exchange";

	String SIMPLE_QUEUE_NAME = "simple_queue_durable";
	String SIMPLE_EXCHANGE_NAME = "simple_exchange";

	String FANOUT_QUEUE_NAME = "fanout_queue";
	String FANOUT_QUEUE_NAME_1 = "fanout_queue_1";
	String FANOUT_QUEUE_NAME_2 = "fanout_queue_2";
	String FANOUT_EXCHANGE_NAME = "fanout_exchange";

	String DIRECT_QUEUE_NAME = "direct_queue";
	String DIRECT_QUEUE_NAME_1 = "direct_queue_1";
	String DIRECT_QUEUE_NAME_2 = "direct_queue_2";
	String DIRECT_EXCHANGE_NAME = "direct_exchange";

	String TOPIC_QUEUE_NAME = "topic_queue";
	String TOPIC_QUEUE_NAME_1 = "topic_queue_1";
	String TOPIC_QUEUE_NAME_2 = "topic_queue_2";
	String TOPIC_EXCHANGE_NAME = "topic_exchange";

	String WORK_ROUND_QUEUE_NAME = "work_round_queue";
	String WORK_ROUND_EXCHANGE_NAME = "work_round_exchange";

	String WORK_FAIR_QUEUE_NAME = "work_fair_queue";
	String WORK_FAIR_EXCHANGE_NAME = "work_fair_exchange";



	DeliverCallback DELIVER_CALLBACK = (consumerTag, message) -> {
		System.out.println("DeliverCallback consumerTag = " + consumerTag);
		System.out.println("DeliverCallback message = " + new String(message.getBody(), StandardCharsets.UTF_8));
	};

	CancelCallback CANCEL_CALLBACK = consumerTag -> System.out.println(" CancelCallback  consumerTag = " + consumerTag);
}
