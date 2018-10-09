package com.zhangchao.mq;

/**
 * @Author zc
 * @Date 2018/10/9 11:18
 **/
public class TestConsumer {
    public static void main(String[] args){
        Consumer Consumer = new Consumer();
        Consumer.init();
        TestConsumer testConsumer = new TestConsumer();
        new Thread(testConsumer.new ConsumerMq(Consumer)).start();
        new Thread(testConsumer.new ConsumerMq(Consumer)).start();
        new Thread(testConsumer.new ConsumerMq(Consumer)).start();
        new Thread(testConsumer.new ConsumerMq(Consumer)).start();
    }

    private class ConsumerMq implements Runnable{
        Consumer Consumer;
        public ConsumerMq(Consumer Consumer){
            this.Consumer = Consumer;
        }

        public void run() {
            while(true){
                try {
                    Consumer.getMessage("Jaycekon-MQ");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
