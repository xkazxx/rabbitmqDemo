package com.xkazxx.work.fairispatch;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xkazxx.common.Constant;
import com.xkazxx.util.ConnectionUtil;

import java.nio.charset.StandardCharsets;

/**
 * work 模式 轮询 方式，生产者的消息轮流发给每个消费者
 *
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2022/12/14 19:23
 **/
public class FairProducer {
	public static void main(String[] args) {
		ConnectionFactory factory = ConnectionUtil.getFactory();
		Connection connection = null;
		Channel channel = null;
		try {
			connection = factory.newConnection("[[work fair producer]]");
			channel = connection.createChannel();
			channel.exchangeDeclare(Constant.WORK_FAIR_EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);
			channel.queueDeclare(Constant.WORK_FAIR_QUEUE_NAME, true, false, false, null);
			channel.queueBind(Constant.WORK_FAIR_QUEUE_NAME, Constant.WORK_FAIR_EXCHANGE_NAME,"");
			for (int i = 0; i < 20; i++) {
				String message = (i + 1) + " hello work fair pattern!";
				channel.basicPublish(Constant.WORK_FAIR_EXCHANGE_NAME,"", null, message.getBytes(StandardCharsets.UTF_8));
			}
			System.out.println("消息发送成功!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(channel);
			ConnectionUtil.closeResource(connection);
		}
	}
}
