package com.zhibei.rabbitMQ;

import com.rabbitmq.client.*;
import com.zhibei.utils.PropertieUtil;

import java.io.IOException;

public class ConsumerApp
{
    public static void main(String[] args)
    {
        Connection connection = null;
        Channel channel = null;
        try
        {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(PropertieUtil.read("Host"));
            factory.setPort(PropertieUtil.readInteger("Port"));
            factory.setUsername(PropertieUtil.read("ConsumerUsername"));
            factory.setPassword(PropertieUtil.read("Password"));
            factory.setVirtualHost(PropertieUtil.read("VirtualHost"));
            connection = factory.newConnection();
            channel = connection.createChannel();

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" Consumer have received '" + message + "'");
                }
            };
            channel.basicConsume("firstQueue", true, consumer);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

