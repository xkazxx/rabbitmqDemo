package com.xkazxx.springboot.consumer;

import com.xkazxx.springboot.common.Constant;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2022/12/14 21:48
 **/
@RabbitListener(bindings = @QueueBinding(
		value = @Queue(value = Constant.FANOUT_QUEUE_NAME_2, autoDelete = "false"),
		exchange = @Exchange(value = Constant.FANOUT_EXCHANGE_NAME, type = ExchangeTypes.FANOUT)))
@Component
public class FanoutConsumer3 {
	@RabbitHandler
	public void fanoutConsumer3Service(String message) throws InterruptedException {
		Thread.sleep(1000);
		// 此处省略发邮件的逻辑
		System.out.println("fanoutConsumer3Service-------------->" + message);
	}
}
