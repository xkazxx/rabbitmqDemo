package com.xkazxx.springboot.config;

import com.xkazxx.springboot.common.Constant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2022/12/14 21:25
 **/
@Configuration
public class RabbitMqConfig {

	@Bean
	public FanoutExchange getFanoutExchange() {
		return new FanoutExchange(Constant.FANOUT_EXCHANGE_NAME, true, false);
	}

	@Bean
	public Queue getQueue1() {
		return new Queue(Constant.FANOUT_QUEUE_NAME, true);
	}

	@Bean
	public Queue getQueue2() {
		return new Queue(Constant.FANOUT_QUEUE_NAME, true);
	}

	@Bean
	public Queue getQueue3() {
		return new Queue(Constant.FANOUT_QUEUE_NAME, true);
	}

	@Bean
	public Binding getBing1() {
		return BindingBuilder.bind(getQueue1()).to(getFanoutExchange());
	}

	@Bean
	public Binding getBing2() {
		return BindingBuilder.bind(getQueue2()).to(getFanoutExchange());
	}

	@Bean
	public Binding getBing3() {
		return BindingBuilder.bind(getQueue3()).to(getFanoutExchange());
	}

}
