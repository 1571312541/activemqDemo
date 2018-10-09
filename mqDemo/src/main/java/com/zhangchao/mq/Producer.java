package com.zhangchao.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zc
 * @Date 2018/10/9 14:08
 **/
public class Producer {

    public static void main(String[] args) throws JMSException {
        AtomicInteger count = new AtomicInteger();
        //创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        //获取连接
        Connection connection = factory.createConnection();
        //连接默认关闭，开启连接
        connection.start();
        //获取session  参数 1、否支持事务，2、签收模式
        //AUTO_ACKNOWLEDGE：表示在消费者receive消息的时候自动的签收
        //CLIENT_ACKNOWLEDGE：表示消费者receive消息后必须手动的调用acknowledge()方法进行签收（实际中这种选择较多）
        //DUPS_OK_ACKNOWLEDGE：签不签收无所谓了，只要消费者能够容忍重复的消息接收，当然这样会降低Session的开销
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建一个队列 创建Destination 消息目标，就是消息发送和接受的地点，要么queue，要么topic。
        //Queue queue = session.createQueue("zc_mq");
        Destination queue = session.createQueue("zc_mq");
        //创建生产者
        MessageProducer producer = session.createProducer(queue);
        //设置持久化（PERSISTENT） | 非持久化（NON_PERSISTENT）
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        for ( int i = 0 ; i < 20 ; i++  ){
            int num = count.getAndIncrement();
            //创建信息
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("producer：发送msg  --  "+num);
            System.out.println("producer：发送msg  --  "+num);
            //发送信息
            producer.send(textMessage);
        }
        if ( connection != null ){
            connection.close();
        }


    }




}
