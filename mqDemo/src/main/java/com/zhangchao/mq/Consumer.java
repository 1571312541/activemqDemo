package com.zhangchao.mq;

import org.apache.activemq.CustomDestination;
import org.apache.activemq.spring.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * @Author zc
 * @Date 2018/10/9 14:30
 **/
public class Consumer {

    public static void main(String[] args) throws JMSException, InterruptedException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("zc_mq");
        MessageConsumer consumer = session.createConsumer(queue);

        /**
         * 接收方式  1、同步接受
         */
        while ( true ){
            Thread.sleep(1000);
            // 接收消息，参数：接收消息的超时时间，为0的话则不超时，receive返回下一个消息，但是超时了或者消费者被关闭，返回null
            TextMessage message = (TextMessage) consumer.receive(1000);
            if ( message != null ){
                message.acknowledge();
                System.out.println("consumer : 接收到 ：" + message.getText());
            }else{
                break;
            }
        }
        /**
         * 接收方式  2、异步接受
         */
        while ( true ){

            Thread.sleep(1000);
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {


                }
            });

        }

    }



}
