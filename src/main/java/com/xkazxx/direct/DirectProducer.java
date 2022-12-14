package com.xkazxx.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xkazxx.common.Constant;
import com.xkazxx.util.ConnectionUtil;

import java.nio.charset.StandardCharsets;

/**
 * direct模式 = 发布-订阅模式， 此模式下支持routingKey精确匹配
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2022/12/14 16:47
 **/
public class DirectProducer {

	public static void main(String[] args) {
		ConnectionFactory factory = ConnectionUtil.getFactory();
		Connection connection = null;
		Channel channel = null;
		try {
			connection = factory.newConnection("[[direct producer]]");
			channel = connection.createChannel();
			channel.exchangeDeclare(Constant.DIRECT_EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);
			channel.queueDeclare(Constant.DIRECT_QUEUE_NAME, true, false, false, null);
			channel.queueDeclare(Constant.DIRECT_QUEUE_NAME_1, true, false, false, null);
			channel.queueDeclare(Constant.DIRECT_QUEUE_NAME_2, true, false, false, null);
			channel.queueBind(Constant.DIRECT_QUEUE_NAME, Constant.DIRECT_EXCHANGE_NAME, "direct1");
			channel.queueBind(Constant.DIRECT_QUEUE_NAME_1, Constant.DIRECT_EXCHANGE_NAME, "direct2");
			channel.queueBind(Constant.DIRECT_QUEUE_NAME_2, Constant.DIRECT_EXCHANGE_NAME, "direct3");

			String message = "hello direct pattern!";
			channel.basicPublish(Constant.DIRECT_EXCHANGE_NAME, "direct3", null, message.getBytes(StandardCharsets.UTF_8));
			System.out.println("消息发送成功!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(channel);
			ConnectionUtil.closeResource(connection);
		}
	}
}
