package com.xkazxx.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xkazxx.common.Constant;
import com.xkazxx.util.ConnectionUtil;

/**
 * 简单模式 消息消费者
 *
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2022/12/14 10:43
 **/
public class SimpleConsumer {

	public static void main(String[] args) {
		ConnectionFactory factory = ConnectionUtil.getFactory();
		Connection connection = null;
		Channel channel = null;
		try {
			connection = factory.newConnection("simple consumer");
			channel = connection.createChannel();
			channel.basicConsume(Constant.SIMPLE_QUEUE_NAME, true, Constant.DELIVER_CALLBACK, Constant.CANCEL_CALLBACK);
			System.out.println(Constant.SIMPLE_QUEUE_NAME + " 开始接受消息");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(channel);
			ConnectionUtil.closeResource(connection);
		}
	}

}