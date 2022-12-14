package com.xkazxx.work.fairispatch;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xkazxx.common.Constant;
import com.xkazxx.util.ConnectionUtil;

import java.nio.charset.StandardCharsets;

/**
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2022/12/14 19:36
 **/
public class FairConsumer2 {
	public static void main(String[] args) {
		ConnectionFactory factory = ConnectionUtil.getFactory();
		Connection connection = null;
		Channel channel = null;
		try {
			connection = factory.newConnection("[[work fair consumer]]" + Thread.currentThread().getName());
			channel = connection.createChannel();
			Channel finalChannel = channel;
			finalChannel.basicQos(1);
			channel.basicConsume(
					Constant.WORK_FAIR_QUEUE_NAME, false,
					(consumerTag, message) -> {
						System.out.println("DeliverCallback consumerTag = " + consumerTag);
						System.out.println("DeliverCallback message = " + new String(message.getBody(), StandardCharsets.UTF_8));
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						finalChannel.basicAck(message.getEnvelope().getDeliveryTag(), false);
					},
					Constant.CANCEL_CALLBACK);

			System.out.println("FairConsumer2 开始接受消息");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(channel);
			ConnectionUtil.closeResource(connection);
		}
	}


}
