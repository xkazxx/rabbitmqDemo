package com.xkazxx.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xkazxx.common.Constant;
import com.xkazxx.util.ConnectionUtil;

import java.nio.charset.StandardCharsets;

/**
 * fanout模式 = 发布-订阅模式， 此模式下routingKey不生效
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2022/12/14 16:47
 **/
public class FanOutProducer {

	public static void main(String[] args) {
		ConnectionFactory factory = ConnectionUtil.getFactory();
		Connection connection = null;
		Channel channel = null;
		try {
			connection = factory.newConnection("[[fanout producer]]");
			channel = connection.createChannel();
			channel.exchangeDeclare(Constant.FANOUT_EXCHANGE_NAME, BuiltinExchangeType.FANOUT, true, false, null);
			channel.queueDeclare(Constant.FANOUT_QUEUE_NAME, true, false, false, null);
			channel.queueDeclare(Constant.FANOUT_QUEUE_NAME_1, true, false, false, null);
			channel.queueDeclare(Constant.FANOUT_QUEUE_NAME_2, true, false, false, null);
			channel.queueBind(Constant.FANOUT_QUEUE_NAME, Constant.FANOUT_EXCHANGE_NAME, "fanout_1");
			channel.queueBind(Constant.FANOUT_QUEUE_NAME_1, Constant.FANOUT_EXCHANGE_NAME, "fanout_2");
			channel.queueBind(Constant.FANOUT_QUEUE_NAME_2, Constant.FANOUT_EXCHANGE_NAME, "fanout_3");

			String message = "hello fanout pattern!";
			channel.basicPublish(Constant.FANOUT_EXCHANGE_NAME, "fanout_1", null, message.getBytes(StandardCharsets.UTF_8));
			System.out.println("消息发送成功!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(channel);
			ConnectionUtil.closeResource(connection);
		}
	}
}
