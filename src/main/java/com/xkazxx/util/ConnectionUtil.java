package com.xkazxx.util;

import com.rabbitmq.client.ConnectionFactory;
import com.xkazxx.common.Constant;

/**
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2022/12/14 10:48
 **/
public class ConnectionUtil {
	public static ConnectionFactory getFactory() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(Constant.HOST);
		factory.setPort(Constant.PORT);
		factory.setPassword(Constant.PASSWORD);
		factory.setUsername(Constant.USER_NAME);
		factory.setVirtualHost(Constant.VIRTUAL_HOST);
		return factory;
	}

	public static void closeResource(AutoCloseable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
