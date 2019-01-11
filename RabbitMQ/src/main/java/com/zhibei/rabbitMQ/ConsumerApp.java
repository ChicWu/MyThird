package com.zhibei.rabbitMQ;

import com.rabbitmq.client.*;

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
            factory.setHost("139.199.230.132");
            factory.setPort(5672);
            factory.setUsername("App_Consumer");
            factory.setPassword("password");
            factory.setVirtualHost("App_Virtual");
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

