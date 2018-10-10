package com.zhangchao.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author zc
 * @Date 2018/10/10 10:22
 **/
public class Produce {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("zc_mq2");
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        for (int i = 0 ; i < 20 ; i ++ ){
            TextMessage message = session.createTextMessage("生产者生产一个消息"+i);
            producer.send(message);
            connection.close();
        }

    }

}
