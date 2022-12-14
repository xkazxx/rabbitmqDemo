package com.xkazxx.simple;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xkazxx.common.Constant;
import com.xkazxx.util.ConnectionUtil;

import java.nio.charset.StandardCharsets;

/**
 * 简单模式 生产者 - 消费者
 *
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2022/12/14 10:09
 **/
public class SimpleProducer {

	public static void main(String[] args) {
		ConnectionFactory factory = ConnectionUtil.getFactory();
		Connection connection = null;
		Channel channel = null;
		try {
			connection = factory.newConnection("simple producer");
			channel = connection.createChannel();
			channel.exchangeDeclare(Constant.SIMPLE_EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);
			channel.queueDeclare(Constant.SIMPLE_QUEUE_NAME, true, false, false, null);
			channel.queueBind(Constant.SIMPLE_QUEUE_NAME, Constant.SIMPLE_EXCHANGE_NAME, "");
			String message = "hello simple pattern!!";
			channel.basicPublish("", Constant.SIMPLE_QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
			System.out.println("消息发送成功!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(channel);
			ConnectionUtil.closeResource(connection);
		}
	}


}
