package com.xkazxx.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xkazxx.common.Constant;
import com.xkazxx.util.ConnectionUtil;

import java.nio.charset.StandardCharsets;

/**
 * direct模式 = 发布-订阅模式， 此模式下支持routingKey精确匹配 【*-匹配一段，#-匹配0.1..n   每段字符以.分隔】
 *
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2022/12/14 16:47
 **/
public class TopicProducer {

	public static void main(String[] args) {
		ConnectionFactory factory = ConnectionUtil.getFactory();
		Connection connection = null;
		Channel channel = null;
		try {
			connection = factory.newConnection("[[topic producer]]");
			channel = connection.createChannel();
			channel.exchangeDeclare(Constant.TOPIC_EXCHANGE_NAME, BuiltinExchangeType.TOPIC, true, false, null);
			channel.queueDeclare(Constant.TOPIC_QUEUE_NAME, true, false, false, null);
			channel.queueDeclare(Constant.TOPIC_QUEUE_NAME_1, true, false, false, null);
			channel.queueDeclare(Constant.TOPIC_QUEUE_NAME_2, true, false, false, null);
			channel.queueBind(Constant.TOPIC_QUEUE_NAME, Constant.TOPIC_EXCHANGE_NAME, "*.direct");
			channel.queueBind(Constant.TOPIC_QUEUE_NAME_1, Constant.TOPIC_EXCHANGE_NAME, "direct.#");
			channel.queueBind(Constant.TOPIC_QUEUE_NAME_2, Constant.TOPIC_EXCHANGE_NAME, "#.direct");

			String message = "hello topic pattern!";
			channel.basicPublish(Constant.TOPIC_EXCHANGE_NAME, "direct", null, message.getBytes(StandardCharsets.UTF_8));
			System.out.println("消息发送成功!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(channel);
			ConnectionUtil.closeResource(connection);
		}
	}
}
