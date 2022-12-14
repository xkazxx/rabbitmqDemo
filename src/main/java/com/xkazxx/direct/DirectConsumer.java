package com.xkazxx.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xkazxx.common.Constant;
import com.xkazxx.util.ConnectionUtil;

/**
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2022/12/14 16:47
 **/
public class DirectConsumer {

	public static void main(String[] args) {
		Thread thread = new Thread(runnable);
		thread.setName(Constant.DIRECT_QUEUE_NAME);
		thread.start();
		Thread thread1 = new Thread(runnable);
		thread1.setName(Constant.DIRECT_QUEUE_NAME_1);
		thread1.start();
		Thread thread2 = new Thread(runnable);
		thread2.setName(Constant.DIRECT_QUEUE_NAME_2);
		thread2.start();
	}

	public static Runnable runnable = () -> {
		ConnectionFactory factory = ConnectionUtil.getFactory();
		Connection connection = null;
		Channel channel = null;
		try {
			connection = factory.newConnection("[[direct consumer]]" + Thread.currentThread().getName());
			channel = connection.createChannel();
			String queueName= Thread.currentThread().getName();
			channel.basicConsume(queueName, true,Constant.DELIVER_CALLBACK, Constant.CANCEL_CALLBACK);
			System.out.println(queueName + " 开始接受消息");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeResource(channel);
			ConnectionUtil.closeResource(connection);
		}
	};
}
