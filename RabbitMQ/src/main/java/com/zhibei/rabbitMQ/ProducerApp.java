package com.zhibei.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerApp
{
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("139.199.230.132");
            factory.setPort(5672);
            factory.setUsername("App_Producer");
            factory.setPassword("password");
            factory.setVirtualHost("App_Virtual");
            //创建与RabbitMQ服务器的TCP连接
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare("firstQueue", true, false, false, null);
            String message = "hello MQword";
            channel.basicPublish("", "firstQueue", null, message.getBytes());
            System.out.println("Send Message is:'" + message + "'");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
